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
            lastCommandSize = 0
            lastContourIndex = 0
            lastContourDistance = 0f
        }

    private val contours = mutableListOf<Contour>()
    private val measure = PathMeasure()

    private var lastCommandSize = 0
    private var lastContourIndex = 0
    private var lastContourDistance = 0f

    fun getContours(): List<Contour> {
        return when {
            commands.size > lastCommandSize -> {
                // add contours from current position
                createContours()
            }
            commands.size < lastCommandSize -> {
                // find contours from beginning
                lastContourIndex = 0
                lastContourDistance = 0f
                contours.clear()
                createContours()
            }
            else -> {
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
        lastCommandSize = commands.size
        return contours
    }

}