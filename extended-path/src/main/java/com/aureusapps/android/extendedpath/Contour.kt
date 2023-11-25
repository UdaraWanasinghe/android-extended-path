package com.aureusapps.android.extendedpath

import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.min

data class Contour(val points: List<Point>, val closed: Boolean) {

    companion object {

        private fun distanceBetweenPoints(point1: Point, point2: Point): Float {
            return hypot(point1.x - point2.x, point1.y - point2.y)
        }

        private fun arePointsEqual(
            points1: List<Point>,
            points2: List<Point>,
            errorTolerance: Float,
            exit: Boolean = false
        ): Boolean {
            if (points1.size != points2.size) return false
            if (points1.isEmpty()) return true
            for (i in points1.indices) {
                val p1 = points1[i]
                val p2 = points2[i]
                if (distanceBetweenPoints(p1, p2) > errorTolerance) {
                    if (exit) return false
                    return arePointsEqual(points1.asReversed(), points2, errorTolerance, true)
                }
            }
            return true
        }

        private fun isInsidePolygon(points: List<Point>, x: Float, y: Float): Boolean {
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

        private fun isOnPolygon(
            points: List<Point>,
            x: Float,
            y: Float,
            errorTolerance: Float
        ): Boolean {
            var x1 = points[0].x
            var y1 = points[0].y
            for (j in 1 until points.size) {
                val x2 = points[j].x
                val y2 = points[j].y
                val dist = shortestDistanceToLineSegment(x, y, x1, y1, x2, y2)
                if (dist <= errorTolerance) return true
                x1 = x2
                y1 = y2
            }
            return false
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

    /**
     * Check if the given point is on or inside the path if the path is closed.
     *
     * @param x X coordinate of the point.
     * @param y Y coordinate of the point.
     * @param errorTolerance The allowed error radius.
     * @param checkInside If [CheckInside.YES], always checks inside.
     * If [CheckInside.NO], inside is not checked.
     * If [CheckInside.IF_CLOSED] checked only if path is closed.
     * @see <a href="https://www.eecs.umich.edu/courses/eecs380/HANDOUTS/PROJ2/InsidePoly.html">https://www.eecs.umich.edu/courses/eecs380/HANDOUTS/PROJ2/InsidePoly.html</a>
     * @see <a href="https://stackoverflow.com/a/6853926/9470914">https://stackoverflow.com/a/6853926/9470914</a>
     */
    fun doIntersect(
        x: Float,
        y: Float,
        errorTolerance: Float,
        checkInside: CheckInside
    ): Boolean {
        if (points.isEmpty()) return false
        return if (shouldCheckInside(checkInside)) {
            isInsidePolygon(points.asReversed(), x, y)
        } else {
            isOnPolygon(points.asReversed(), x, y, errorTolerance)
        }
    }

    fun isEquals(contour: Contour, errorTolerance: Float): Boolean {
        return arePointsEqual(points, contour.points, errorTolerance)
    }

    private fun shouldCheckInside(checkInside: CheckInside): Boolean {
        return when (checkInside) {
            CheckInside.YES -> true
            CheckInside.NO -> false
            CheckInside.IF_CLOSED -> closed
        }
    }

}