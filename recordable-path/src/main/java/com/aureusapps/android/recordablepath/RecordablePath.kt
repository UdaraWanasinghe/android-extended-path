package com.aureusapps.android.recordablepath

import android.graphics.Matrix
import android.graphics.Path
import com.aureusapps.android.recordablepath.commands.*
import com.aureusapps.android.recordablepath.commands.Set
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
class RecordablePath(
    private var commands: MutableList<PathCommand> = mutableListOf()
) : Path() {

    companion object {
        fun fromJson(json: String): RecordablePath {
            return Json.decodeFromString(json)
        }
    }

    init {
        if (commands.size > 0) {
            val list = commands
            commands = mutableListOf()
            list.forEach { it.execute(this) }
        }
    }

    fun toJson(): String {
        return Json.encodeToString(this)
    }

    override fun moveTo(x: Float, y: Float) {
        super.moveTo(x, y)
        commands.add(MoveTo(x, y))
    }

    override fun rMoveTo(dx: Float, dy: Float) {
        super.rMoveTo(dx, dy)
        commands.add(RMoveTo(dx, dy))
    }

    override fun lineTo(x: Float, y: Float) {
        super.lineTo(x, y)
        commands.add(LineTo(x, y))
    }

    override fun rLineTo(dx: Float, dy: Float) {
        super.rLineTo(dx, dy)
        commands.add(RLineTo(dx, dy))
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
    }

    override fun addCircle(
        x: Float,
        y: Float,
        radius: Float,
        dir: Direction
    ) {
        super.addCircle(x, y, radius, dir)
        commands.add(AddCircle(x, y, radius, dir))
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
    }

    fun addPath(src: RecordablePath) {
        super.addPath(src)
        commands.add(AddPath1(src))
    }

    fun addPath(
        src: RecordablePath,
        matrix: Matrix
    ) {
        super.addPath(src, matrix)
        commands.add(AddPath2(src, matrix))
    }

    fun addPath(
        src: RecordablePath,
        dx: Float,
        dy: Float
    ) {
        super.addPath(src, dx, dy)
        commands.add(AddPath3(src, dx, dy))
    }

    override fun offset(dx: Float, dy: Float) {
        super.offset(dx, dy)
        commands.add(Offset(dx, dy))
    }

    override fun quadTo(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float
    ) {
        super.quadTo(x1, y1, x2, y2)
        commands.add(QuadTo(x1, y1, x2, y2))
    }

    override fun rQuadTo(
        dx1: Float,
        dy1: Float,
        dx2: Float,
        dy2: Float
    ) {
        super.rQuadTo(dx1, dy1, dx2, dy2)
        commands.add(RQuadTo(dx1, dy1, dx2, dy2))
    }

    override fun setFillType(ft: FillType) {
        super.setFillType(ft)
        commands.add(SetFillType(ft))
    }

    override fun setLastPoint(dx: Float, dy: Float) {
        super.setLastPoint(dx, dy)
        commands.add(SetLastPoint(dx, dy))
    }

    override fun incReserve(extraPtCount: Int) {
        super.incReserve(extraPtCount)
        commands.add(IncReserve(extraPtCount))
    }

    override fun toggleInverseFillType() {
        super.toggleInverseFillType()
        commands.add(ToggleInverseFillType)
    }

    fun transform(matrix: Matrix, dst: RecordablePath?) {
        super.transform(matrix, dst)
        commands.add(Transform1(matrix, dst))
    }

    override fun transform(matrix: Matrix) {
        super.transform(matrix)
        commands.add(Transform2(matrix))
    }

    fun set(src: RecordablePath) {
        super.set(src)
        commands.add(Set(src))
    }

    override fun rewind() {
        super.rewind()
        commands.add(Rewind)
    }

    override fun close() {
        super.close()
        commands.add(Close)
    }

    override fun reset() {
        super.reset()
        commands.clear()
    }

}