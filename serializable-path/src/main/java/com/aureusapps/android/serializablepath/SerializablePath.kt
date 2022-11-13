package com.aureusapps.android.serializablepath

import android.graphics.Matrix
import android.graphics.Path
import com.aureusapps.android.serializablepath.commands.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
class SerializablePath {

    companion object {
        fun fromJson(json: String): SerializablePath {
            return Json.decodeFromString(json)
        }
    }

    @Transient
    val path = Path()

    internal var commands: MutableList<PathCommand> = mutableListOf()

    init {
        commands.forEach { it.execute(path) }
    }

    fun toJson(): String {
        return Json.encodeToString(this)
    }

    fun moveTo(x: Float, y: Float) {
        path.moveTo(x, y)
        commands.add(MoveTo(x, y))
    }

    fun rMoveTo(dx: Float, dy: Float) {
        path.rMoveTo(dx, dy)
        commands.add(RMoveTo(dx, dy))
    }

    fun lineTo(x: Float, y: Float) {
        path.lineTo(x, y)
        commands.add(LineTo(x, y))
    }

    fun rLineTo(dx: Float, dy: Float) {
        path.rLineTo(dx, dy)
        commands.add(RLineTo(dx, dy))
    }

    fun cubicTo(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        x3: Float,
        y3: Float
    ) {
        path.cubicTo(x1, y1, x2, y2, x3, y3)
        commands.add(CubicTo(x1, y1, x2, y2, x3, y3))
    }

    fun rCubicTo(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        x3: Float,
        y3: Float
    ) {
        path.rCubicTo(x1, y1, x2, y2, x3, y3)
        commands.add(RCubicTo(x1, y1, x2, y2, x3, y3))
    }

    fun addCircle(
        x: Float,
        y: Float,
        radius: Float,
        dir: Path.Direction
    ) {
        path.addCircle(x, y, radius, dir)
        commands.add(AddCircle(x, y, radius, dir))
    }

    fun addRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        dir: Path.Direction
    ) {
        path.addRect(left, top, right, bottom, dir)
        commands.add(AddRect(left, top, right, bottom, dir))
    }

    fun addRoundRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        radii: FloatArray,
        dir: Path.Direction
    ) {
        path.addRoundRect(left, top, right, bottom, radii, dir)
        commands.add(AddRoundRect1(left, top, right, bottom, radii, dir))
    }

    fun addRoundRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        rx: Float,
        ry: Float,
        dir: Path.Direction
    ) {
        path.addRoundRect(left, top, right, bottom, rx, ry, dir)
        commands.add(AddRoundRect2(left, top, right, bottom, rx, ry, dir))
    }

    fun addArc(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        startAngle: Float,
        sweepAngle: Float
    ) {
        path.addArc(
            left,
            top,
            right,
            bottom,
            startAngle,
            sweepAngle
        )
        commands.add(AddArc(left, top, right, bottom, startAngle, sweepAngle))
    }

    fun arcTo(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        startAngle: Float,
        sweepAngle: Float,
        forceMoveTo: Boolean
    ) {
        path.arcTo(
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

    fun addOval(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        dir: Path.Direction
    ) {
        path.addOval(left, top, right, bottom, dir)
        commands.add(AddOval(left, top, right, bottom, dir))
    }

    fun addPath(src: SerializablePath) {
        path.addPath(src.path)
        commands.add(AddPath1(src))
    }

    fun addPath(
        src: SerializablePath,
        matrix: Matrix
    ) {
        path.addPath(src.path, matrix)
        commands.add(AddPath2(src, matrix))
    }

    fun addPath(
        src: SerializablePath,
        dx: Float,
        dy: Float
    ) {
        path.addPath(src.path, dx, dy)
        commands.add(AddPath3(src, dx, dy))
    }

    fun offset(dx: Float, dy: Float) {
        path.offset(dx, dy)
        commands.add(Offset(dx, dy))
    }

    fun quadTo(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float
    ) {
        path.quadTo(x1, y1, x2, y2)
        commands.add(QuadTo(x1, y1, x2, y2))
    }

    fun rQuadTo(
        dx1: Float,
        dy1: Float,
        dx2: Float,
        dy2: Float
    ) {
        path.rQuadTo(dx1, dy1, dx2, dy2)
        commands.add(RQuadTo(dx1, dy1, dx2, dy2))
    }

    fun setFillType(ft: Path.FillType) {
        path.fillType = ft
        commands.add(SetFillType(ft))
    }

    fun setLastPoint(dx: Float, dy: Float) {
        path.setLastPoint(dx, dy)
        commands.add(SetLastPoint(dx, dy))
    }

    fun incReserve(extraPtCount: Int) {
        path.incReserve(extraPtCount)
        commands.add(IncReserve(extraPtCount))
    }

    fun toggleInverseFillType() {
        path.toggleInverseFillType()
        commands.add(ToggleInverseFillType)
    }

    fun transform(matrix: Matrix) {
        path.transform(matrix)
        commands.add(Transform(matrix))
    }

    fun rewind() {
        path.rewind()
        commands.add(Rewind)
    }

    fun close() {
        path.close()
        commands.add(Close)
    }

    fun reset() {
        path.reset()
        commands.clear()
    }

}