package com.aureusapps.android.serializablepath

import android.graphics.Matrix
import android.graphics.Path
import com.aureusapps.android.serializablepath.commands.*

class SerializablePath {

    companion object {
        fun fromPathData(pathData: String): Path {

            return Path()
        }
    }

    val path = Path()
    var commands = mutableListOf<Command>()

    fun toPathData(): String {
        return commands.joinToString(separator = "") { command -> command.toPathData() }
    }

    fun moveTo(x: Float, y: Float) {
        path.moveTo(x, y)
        commands.add(MoveTo(x, y))
    }

    fun rMoveTo(dx: Float, dy: Float) {
        path.rMoveTo(dx, dy)
        commands.add(RMoveTo(dx, dy))
    }

    fun addRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        dir: Path.Direction = Path.Direction.CW
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
        dir: Path.Direction = Path.Direction.CW
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
        dir: Path.Direction = Path.Direction.CW
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
        path.addArc(left, top, right, bottom, startAngle, sweepAngle)
        commands.add(AddArc(left, top, right, bottom, startAngle, sweepAngle))
    }

    fun addCircle(
        x: Float,
        y: Float,
        radius: Float,
        dir: Path.Direction = Path.Direction.CW
    ) {
        path.addCircle(x, y, radius, dir)
        commands.add(AddCircle(x, y, radius, dir))
    }

    fun addOval(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        dir: Path.Direction = Path.Direction.CW
    ) {
        path.addOval(left, top, right, bottom, dir)
        commands.add(AddOval(left, top, right, bottom, dir))
    }

    fun addPath(src: SerializablePath) {
        if (isClosed == false) {
            commands.add(Close())
        }
        path.addPath(src.path)
        commands.add(AddPath(src))
    }

    fun lineTo(x: Float, y: Float) {
        path.lineTo(x, y)
        commands.add(LineTo(x, y, moveToOrigin))
    }

    fun rLineTo(dx: Float, dy: Float) {
        path.rLineTo(dx, dy)
        commands.add(RLineTo(dx, dy, moveToOrigin))
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
        commands.add(CubicTo(x1, y1, x2, y2, x3, y3, moveToOrigin))
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
        commands.add(RCubicTo(x1, y1, x2, y2, x3, y3, moveToOrigin))
    }

    fun quadTo(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float
    ) {
        path.quadTo(x1, y1, x2, y2)
        commands.add(QuadTo(x1, y1, x2, y2, moveToOrigin))
    }

    fun rQuadTo(
        dx1: Float,
        dy1: Float,
        dx2: Float,
        dy2: Float
    ) {
        path.rQuadTo(dx1, dy1, dx2, dy2)
        commands.add(RQuadTo(dx1, dy1, dx2, dy2, moveToOrigin))
    }

    fun set(src: SerializablePath) {
        path.set(src.path)
        commands = src.commands
    }

    fun transform(matrix: Matrix) {
        path.transform(matrix)
    }

    fun close() {
        if (isClosed == false) {
            path.close()
            commands.add(Close())
        }
    }

    fun reset() {
        path.reset()
        commands.clear()
    }

    internal val isClosed: Boolean? get() = commands.lastOrNull()?.isClosed

    private val moveToOrigin: Boolean get() = commands.lastOrNull()?.isClosed ?: true

}