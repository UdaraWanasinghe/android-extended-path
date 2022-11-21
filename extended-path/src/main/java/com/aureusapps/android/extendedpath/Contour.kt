package com.aureusapps.android.extendedpath

import android.graphics.PointF
import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.min

data class Contour(val points: List<PointF>, val closed: Boolean) {

    /**
     * Check if the given point is on or inside the path if the path is closed.
     *
     * @param x X coordinate of the point.
     * @param y Y coordinate of the point.
     * @param precision The allowed error radius.
     * @param ignoreInside If true, only checked if the point is on the path.
     * @see <a href="https://www.eecs.umich.edu/courses/eecs380/HANDOUTS/PROJ2/InsidePoly.html">https://www.eecs.umich.edu/courses/eecs380/HANDOUTS/PROJ2/InsidePoly.html</a>
     * @see <a href="https://stackoverflow.com/a/6853926/9470914">https://stackoverflow.com/a/6853926/9470914</a>
     */
    fun doIntersect(
        x: Float,
        y: Float,
        precision: Float,
        ignoreInside: Boolean
    ): Boolean {
        if (points.isNotEmpty()) {
            if (!ignoreInside && closed) {
                // check if inside the contour
                if (isInsidePolygon(points, x, y)) return true
            } else {
                // check if on the contour
                var x1 = points[0].x
                var y1 = points[0].y
                for (j in 1 until points.size) {
                    val x2 = points[j].x
                    val y2 = points[j].y
                    val dist = shortestDistanceToLineSegment(x, y, x1, y1, x2, y2)
                    if (dist <= precision) return true
                    x1 = x2
                    y1 = y2
                }
            }
        }
        return false
    }

    private fun isInsidePolygon(points: List<PointF>, x: Float, y: Float): Boolean {
        var x1 = points[points.size - 1].x
        var y1 = points[points.size - 1].y
        var count = 0
        for (j in points.indices) {
            val x2 = points[j].x
            val y2 = points[j].y
            if (y > min(y1, y2)) {
                if (y <= max(y1, y2)) {
                    if (x <= max(x1, x2)) {
                        if (y1 != y2) {
                            val xInters = (y - y1) * (x2 - x1) / (y2 - y1) + x1
                            if (x1 == x2 || x <= xInters) {
                                count++
                            }
                        }
                    }
                }
            }
            x1 = x2
            y1 = y2
        }
        return count % 2 != 0
    }

    private fun shortestDistanceToLineSegment(
        x: Float,
        y: Float,
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float
    ): Float {
        // based on projection vectors
        val a = x - x1
        val b = y - y1
        val c = x2 - x1
        val d = y2 - y1

        val len2 = c * c + d * d
        val param = if (len2 != 0f) {
            val dot = a * c + b * d
            dot / len2
        } else {
            -1f
        }

        val (xx, yy) = when {
            param < 0f -> x1 to y1
            param > 1f -> x2 to y2
            else -> x1 + param * c to y1 + param * d
        }

        val dx = x - xx
        val dy = y - yy
        return hypot(dx, dy)
    }

}
