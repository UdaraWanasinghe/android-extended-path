package com.aureusapps.android.extendedpath

import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.PointF
import com.aureusapps.android.extendedpath.commands.Command
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class BasePath : Path() {

    protected val commands = mutableListOf<Command>()
    var precision = 4f
        set(value) {
            field = value
            contourState = ContourState.RECREATE
            lastContourIndex = 0
            lastContourDistance = 0f
        }

    @Transient
    private val contours = mutableListOf<Contour>()

    @Transient
    private val measure = PathMeasure()

    @Transient
    private var contourState = ContourState.RECREATE

    @Transient
    private var lastContourIndex = 0

    @Transient
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