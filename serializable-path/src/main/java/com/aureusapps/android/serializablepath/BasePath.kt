package com.aureusapps.android.serializablepath

import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.PointF
import com.aureusapps.android.serializablepath.commands.Command

abstract class BasePath : Path() {

    protected val commands = mutableListOf<Command>()
    var precision = 4f
        set(value) {
            field = value
            contourState = ContourState.RECREATE
            lastContourIndex = 0
            lastContourDistance = 0f
        }

    private val contours = mutableListOf<Contour>()
    private val measure = PathMeasure()

    private var contourState = ContourState.RECREATE
    private var lastContourIndex = 0
    private var lastContourDistance = 0f

    fun getContours(): List<Contour> {
        return when (contourState) {
            ContourState.CHANGED -> {
                createContours()
            }
            ContourState.RECREATE -> {
                lastContourIndex = 0
                lastContourDistance = 0f
                contours.clear()
                createContours()
            }
            ContourState.UNCHANGED -> {
                contours
            }
        }
    }

    @Synchronized
    private fun createContours(): List<Contour> {
        measure.setPath(this, false)
        var contourIndex = -1
        var dis = lastContourDistance
        val pos = FloatArray(2)
        do {
            if (++contourIndex < lastContourIndex) {
                continue
            }
            val points = if (dis == 0f) {
                mutableListOf()
            } else {
                contours.removeLast().points as MutableList<PointF>
            }
            val len = measure.length
            while (dis <= len) {
                measure.getPosTan(dis, pos, null)
                points.add(PointF(pos[0], pos[1]))
                dis += precision
            }
            contours.add(Contour(points, measure.isClosed))
            lastContourDistance = dis
            dis = 0f
        } while (measure.nextContour())
        lastContourIndex = contourIndex
        flogContoursUnchanged()
        return contours
    }

    private fun flogContoursUnchanged() {
        contourState = ContourState.UNCHANGED
    }

    protected fun flagContoursChanged() {
        contourState = ContourState.CHANGED
    }

    protected fun flagContoursRecreate() {
        contourState = ContourState.RECREATE
    }

    protected enum class ContourState {
        UNCHANGED, CHANGED, RECREATE
    }

}