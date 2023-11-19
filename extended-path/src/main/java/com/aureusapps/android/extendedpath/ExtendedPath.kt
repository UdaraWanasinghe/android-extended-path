package com.aureusapps.android.extendedpath

import android.graphics.Matrix
import android.graphics.Path
import com.aureusapps.android.extendedpath.commands.AddArc
import com.aureusapps.android.extendedpath.commands.AddCircle
import com.aureusapps.android.extendedpath.commands.AddOval
import com.aureusapps.android.extendedpath.commands.AddPath1
import com.aureusapps.android.extendedpath.commands.AddPath2
import com.aureusapps.android.extendedpath.commands.AddPath3
import com.aureusapps.android.extendedpath.commands.AddRect
import com.aureusapps.android.extendedpath.commands.AddRoundRect1
import com.aureusapps.android.extendedpath.commands.AddRoundRect2
import com.aureusapps.android.extendedpath.commands.ArcTo
import com.aureusapps.android.extendedpath.commands.Close
import com.aureusapps.android.extendedpath.commands.Command
import com.aureusapps.android.extendedpath.commands.CubicTo
import com.aureusapps.android.extendedpath.commands.IncReserve
import com.aureusapps.android.extendedpath.commands.LineTo
import com.aureusapps.android.extendedpath.commands.MoveTo
import com.aureusapps.android.extendedpath.commands.Offset1
import com.aureusapps.android.extendedpath.commands.Offset2
import com.aureusapps.android.extendedpath.commands.QuadTo
import com.aureusapps.android.extendedpath.commands.RCubicTo
import com.aureusapps.android.extendedpath.commands.RLineTo
import com.aureusapps.android.extendedpath.commands.RMoveTo
import com.aureusapps.android.extendedpath.commands.RQuadTo
import com.aureusapps.android.extendedpath.commands.SetFillType
import com.aureusapps.android.extendedpath.commands.SetLastPoint
import com.aureusapps.android.extendedpath.commands.ToggleInverseFillType
import com.aureusapps.android.extendedpath.commands.Transform1
import com.aureusapps.android.extendedpath.commands.Transform2
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
class ExtendedPath : Path() {

    companion object {
        fun fromJson(json: String): ExtendedPath {
            return Json.decodeFromString(json)
        }
    }

    private val commands = mutableListOf<Command>()

    @Transient
    private val pathContourGenerator = PathContourGenerator(this)

    init {
        initPath()
    }

    private fun initPath() {
        if (commands.isNotEmpty()) {
            val p = Path()
            commands.forEach { it.execute(p) }
            super.set(p)
        }
    }

    fun getContours(): List<Contour> = pathContourGenerator.getContours()

    fun doIntersect(
        x: Float,
        y: Float,
        precision: Float = pathContourGenerator.precision,
        checkInside: CheckInside = CheckInside.IF_CLOSED
    ): Boolean {
        val contours = getContours()
        for (contour in contours) {
            if (contour.doIntersect(x, y, precision, checkInside)) {
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
        precision: Float = pathContourGenerator.precision,
        checkInside: CheckInside = CheckInside.IF_CLOSED
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
                            checkInside
                        )
                    ) {
                        return true
                    }
                }
        }
        return false
    }

    fun toJson(): String {
        return Json.encodeToString(this)
    }

    override fun moveTo(x: Float, y: Float) {
        super.moveTo(x, y)
        commands.add(MoveTo(x, y))
        pathContourGenerator.flagContoursChanged()
    }

    override fun rMoveTo(dx: Float, dy: Float) {
        super.rMoveTo(dx, dy)
        commands.add(RMoveTo(dx, dy))
        pathContourGenerator.flagContoursChanged()
    }

    override fun lineTo(x: Float, y: Float) {
        super.lineTo(x, y)
        commands.add(LineTo(x, y))
        pathContourGenerator.flagContoursChanged()
    }

    override fun rLineTo(dx: Float, dy: Float) {
        super.rLineTo(dx, dy)
        commands.add(RLineTo(dx, dy))
        pathContourGenerator.flagContoursChanged()
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
        pathContourGenerator.flagContoursChanged()
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
        pathContourGenerator.flagContoursChanged()
    }

    override fun addCircle(
        x: Float,
        y: Float,
        radius: Float,
        dir: Direction
    ) {
        super.addCircle(x, y, radius, dir)
        commands.add(AddCircle(x, y, radius, dir))
        pathContourGenerator.flagContoursChanged()
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
        pathContourGenerator.flagContoursChanged()
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
        pathContourGenerator.flagContoursChanged()
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
        pathContourGenerator.flagContoursChanged()
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
        pathContourGenerator.flagContoursChanged()
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
        pathContourGenerator.flagContoursChanged()
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
        pathContourGenerator.flagContoursChanged()
    }

    override fun quadTo(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float
    ) {
        super.quadTo(x1, y1, x2, y2)
        commands.add(QuadTo(x1, y1, x2, y2))
        pathContourGenerator.flagContoursChanged()
    }

    override fun rQuadTo(
        dx1: Float,
        dy1: Float,
        dx2: Float,
        dy2: Float
    ) {
        super.rQuadTo(dx1, dy1, dx2, dy2)
        commands.add(RQuadTo(dx1, dy1, dx2, dy2))
        pathContourGenerator.flagContoursChanged()
    }

    fun addPath(src: ExtendedPath) {
        super.addPath(src)
        commands.add(AddPath1(src))
        pathContourGenerator.flagContoursChanged()
    }

    fun addPath(
        src: ExtendedPath,
        matrix: Matrix
    ) {
        super.addPath(src, matrix)
        commands.add(AddPath2(src, matrix))
        pathContourGenerator.flagContoursChanged()
    }

    fun addPath(
        src: ExtendedPath,
        dx: Float,
        dy: Float
    ) {
        super.addPath(src, dx, dy)
        commands.add(AddPath3(src, dx, dy))
        pathContourGenerator.flagContoursChanged()
    }

    override fun offset(dx: Float, dy: Float) {
        super.offset(dx, dy)
        commands.add(Offset1(dx, dy))
        pathContourGenerator.flagContoursRecreate()
    }

    fun offset(dx: Float, dy: Float, dst: ExtendedPath?) {
        super.offset(dx, dy, dst)
        commands.add(Offset2(dx, dy, dst))
        pathContourGenerator.flagContoursRecreate()
    }

    override fun setFillType(ft: FillType) {
        super.setFillType(ft)
        commands.add(SetFillType(ft))
    }

    override fun setLastPoint(dx: Float, dy: Float) {
        super.setLastPoint(dx, dy)
        commands.add(SetLastPoint(dx, dy))
        pathContourGenerator.flagContoursChanged()
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
        pathContourGenerator.flagContoursRecreate()
    }

    fun transform(matrix: Matrix, dst: ExtendedPath?) {
        super.transform(matrix, dst)
        commands.add(Transform2(matrix, dst))
        pathContourGenerator.flagContoursRecreate()
    }

    override fun close() {
        super.close()
        commands.add(Close)
        pathContourGenerator.flagContoursChanged()
    }

    override fun rewind() {
        super.rewind()
        commands.clear()
        pathContourGenerator.flagContoursRecreate()
    }

    override fun reset() {
        super.reset()
        commands.clear()
        pathContourGenerator.flagContoursRecreate()
    }

}