package com.aureusapps.android.extendedpath

import android.graphics.Matrix
import android.graphics.Path
import com.aureusapps.android.extendedpath.commands.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
class ExtendedPath : BasePath() {

    companion object {
        fun fromJson(json: String): ExtendedPath {
            return Json.decodeFromString(json)
        }
    }

    init {
        initiatePath()
    }

    private fun initiatePath() {
        if (commands.isNotEmpty()) {
            val p = Path()
            commands.forEach { it.execute(p) }
            super.set(p)
        }
    }

    fun toJson(): String {
        return Json.encodeToString(this)
    }

    fun doIntersect(
        x: Float,
        y: Float,
        precision: Float = this.precision,
        ignoreInside: Boolean = false
    ): Boolean {
        val contours = getContours()
        for (contour in contours) {
            if (contour.doIntersect(x, y, precision, ignoreInside)) {
                return true
            }
        }
        return false
    }

    /**
     * Returns if the given path intersects with this path.
     *
     * @param path The path to check if intersecting.
     * @param precision The allowed error radius.
     */
    fun doIntersect(
        path: ExtendedPath,
        precision: Float = this.precision,
        ignoreInside: Boolean = false
    ): Boolean {
        val contours1 = getContours()
        val contours2 = path.getContours()
        contours1.forEach { contour1 ->
            contours2
                .flatMap { contour2 ->
                    contour2.points
                }
                .forEach { point ->
                    if (contour1.doIntersect(
                            point.x,
                            point.y,
                            precision,
                            ignoreInside)
                    ) {
                        return true
                    }
                }
        }
        return false
    }

    override fun moveTo(x: Float, y: Float) {
        super.moveTo(x, y)
        commands.add(MoveTo(x, y))
        flagContoursChanged()
    }

    override fun rMoveTo(dx: Float, dy: Float) {
        super.rMoveTo(dx, dy)
        commands.add(RMoveTo(dx, dy))
        flagContoursChanged()
    }

    override fun lineTo(x: Float, y: Float) {
        super.lineTo(x, y)
        commands.add(LineTo(x, y))
        flagContoursChanged()
    }

    override fun rLineTo(dx: Float, dy: Float) {
        super.rLineTo(dx, dy)
        commands.add(RLineTo(dx, dy))
        flagContoursChanged()
    }

    override fun cubicTo(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        x3: Float,
        y3: Float
    ) {
        super.cubicTo(x1, y1, x2, y2, x3, y3)
        commands.add(CubicTo(x1, y1, x2, y2, x3, y3))
        flagContoursChanged()
    }

    override fun rCubicTo(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        x3: Float,
        y3: Float
    ) {
        super.rCubicTo(x1, y1, x2, y2, x3, y3)
        commands.add(RCubicTo(x1, y1, x2, y2, x3, y3))
        flagContoursChanged()
    }

    override fun addCircle(
        x: Float,
        y: Float,
        radius: Float,
        dir: Direction
    ) {
        super.addCircle(x, y, radius, dir)
        commands.add(AddCircle(x, y, radius, dir))
        flagContoursChanged()
    }

    override fun addRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        dir: Direction
    ) {
        super.addRect(left, top, right, bottom, dir)
        commands.add(AddRect(left, top, right, bottom, dir))
        flagContoursChanged()
    }

    override fun addRoundRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        radii: FloatArray,
        dir: Direction
    ) {
        super.addRoundRect(left, top, right, bottom, radii, dir)
        commands.add(AddRoundRect1(left, top, right, bottom, radii, dir))
        flagContoursChanged()
    }

    override fun addRoundRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        rx: Float,
        ry: Float,
        dir: Direction
    ) {
        super.addRoundRect(left, top, right, bottom, rx, ry, dir)
        commands.add(AddRoundRect2(left, top, right, bottom, rx, ry, dir))
        flagContoursChanged()
    }

    override fun addArc(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        startAngle: Float,
        sweepAngle: Float
    ) {
        super.addArc(
            left,
            top,
            right,
            bottom,
            startAngle,
            sweepAngle
        )
        commands.add(AddArc(left, top, right, bottom, startAngle, sweepAngle))
        flagContoursChanged()
    }

    override fun arcTo(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        startAngle: Float,
        sweepAngle: Float,
        forceMoveTo: Boolean
    ) {
        super.arcTo(
            left,
            top,
            right,
            bottom,
            startAngle,
            sweepAngle,
            forceMoveTo
        )
        commands.add(ArcTo(left, top, right, bottom, startAngle, sweepAngle, forceMoveTo))
        flagContoursChanged()
    }

    override fun addOval(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        dir: Direction
    ) {
        super.addOval(left, top, right, bottom, dir)
        commands.add(AddOval(left, top, right, bottom, dir))
        flagContoursChanged()
    }

    override fun quadTo(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float
    ) {
        super.quadTo(x1, y1, x2, y2)
        commands.add(QuadTo(x1, y1, x2, y2))
        flagContoursChanged()
    }

    override fun rQuadTo(
        dx1: Float,
        dy1: Float,
        dx2: Float,
        dy2: Float
    ) {
        super.rQuadTo(dx1, dy1, dx2, dy2)
        commands.add(RQuadTo(dx1, dy1, dx2, dy2))
        flagContoursChanged()
    }

    fun addPath(src: ExtendedPath) {
        super.addPath(src)
        commands.add(AddPath1(src))
        flagContoursChanged()
    }

    fun addPath(
        src: ExtendedPath,
        matrix: Matrix
    ) {
        super.addPath(src, matrix)
        commands.add(AddPath2(src, matrix))
        flagContoursChanged()
    }

    fun addPath(
        src: ExtendedPath,
        dx: Float,
        dy: Float
    ) {
        super.addPath(src, dx, dy)
        commands.add(AddPath3(src, dx, dy))
        flagContoursChanged()
    }

    override fun offset(dx: Float, dy: Float) {
        super.offset(dx, dy)
        commands.add(Offset1(dx, dy))
        flagContoursRecreate()
    }

    fun offset(dx: Float, dy: Float, dst: ExtendedPath?) {
        super.offset(dx, dy, dst)
        commands.add(Offset2(dx, dy, dst))
        flagContoursRecreate()
    }

    override fun setFillType(ft: FillType) {
        super.setFillType(ft)
        commands.add(SetFillType(ft))
    }

    override fun setLastPoint(dx: Float, dy: Float) {
        super.setLastPoint(dx, dy)
        commands.add(SetLastPoint(dx, dy))
        flagContoursChanged()
    }

    override fun incReserve(extraPtCount: Int) {
        super.incReserve(extraPtCount)
        commands.add(IncReserve(extraPtCount))
    }

    override fun toggleInverseFillType() {
        super.toggleInverseFillType()
        commands.add(ToggleInverseFillType)
    }

    override fun transform(matrix: Matrix) {
        super.transform(matrix)
        val last = commands.lastOrNull()
        if (last is Transform1) {
            last.concat(matrix)
        } else {
            commands.add(Transform1(matrix))
        }
        flagContoursRecreate()
    }

    fun transform(matrix: Matrix, dst: ExtendedPath?) {
        super.transform(matrix, dst)
        commands.add(Transform2(matrix, dst))
        flagContoursRecreate()
    }

    override fun close() {
        super.close()
        commands.add(Close)
        flagContoursChanged()
    }

    override fun rewind() {
        super.rewind()
        commands.clear()
        flagContoursRecreate()
    }

    override fun reset() {
        super.reset()
        commands.clear()
        flagContoursRecreate()
    }

}