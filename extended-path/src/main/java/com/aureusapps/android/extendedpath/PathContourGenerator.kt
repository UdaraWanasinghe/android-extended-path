package com.aureusapps.android.extendedpath

import android.graphics.Path
import android.graphics.PathMeasure

class PathContourGenerator(private val path: Path) {

    private enum class ContourState {
        UNCHANGED, CHANGED, RECREATE
    }

    private val contours = mutableListOf<Contour>()
    private val measure = PathMeasure()
    private var contourState = ContourState.RECREATE
    private var lastContourIndex = 0
    private var lastContourDistance = 0f

    var measureDistance = 4f
        set(value) {
            if (field == value) return
            field = value
            contourState = ContourState.RECREATE
        }

    fun getContours(): List<Contour> {
        return when (contourState) {
            ContourState.CHANGED -> createContours()

            ContourState.RECREATE -> {
                lastContourIndex = 0
                lastContourDistance = 0f
                contours.clear()
                createContours()
            }

            ContourState.UNCHANGED -> contours
        }
    }

    private fun createContours(): List<Contour> {
        measure.setPath(path, false)
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
                contours.removeLast().points as MutableList<Point>
            }
            val len = measure.length
            while (dis <= len) {
                measure.getPosTan(dis, pos, null)
                points.add(Point(pos[0], pos[1], dis))
                dis += measureDistance
            }
            contours.add(Contour(points, measure.isClosed))
            lastContourDistance = dis
            dis = 0f
        } while (measure.nextContour())
        lastContourIndex = contourIndex
        flagContoursUnchanged()
        return contours
    }

    private fun flagContoursUnchanged() {
        contourState = ContourState.UNCHANGED
    }

    fun flagContoursChanged() {
        contourState = ContourState.CHANGED
    }

    fun flagContoursRecreate() {
        contourState = ContourState.RECREATE
    }

}