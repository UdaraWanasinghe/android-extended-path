package com.aureusapps.android.serializablepath

import android.graphics.Matrix
import android.graphics.Path
import android.graphics.RectF
import com.aureusapps.android.serializablepath.cmds.*

class VectorPath {

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

    fun addRoundRect(left: Float, top: Float, right: Float, bottom: Float, rx: Float, ry: Float, dir: Path.Direction) {
        path.addRoundRect(left, top, right, bottom, rx, ry, dir)
        commands.add(AddRoundRect2(left, top, right, bottom, rx, ry, dir))
    }

    fun addArc(left: Float, top: Float, right: Float, bottom: Float, startAngle: Float, sweepAngle: Float) {
        path.addArc(left, top, right, bottom, startAngle, sweepAngle)
        commands.add(AddArc(left, top, right, bottom, startAngle, sweepAngle))
    }

    fun addCircle(x: Float, y: Float, radius: Float, dir: Path.Direction) {
        path.addCircle(x, y, radius, dir)
        commands.add(AddCircle(x, y, radius, dir))
    }

    fun addOval(left: Float, top: Float, right: Float, bottom: Float, dir: Path.Direction) {
        path.addOval(left, top, right, bottom, dir)
        commands.add(AddOval(left, top, right, bottom, dir))
    }

    fun addPath(src: VectorPath) {
        path.addPath(src.path)
        commands.add(AddPath(src))
    }

    fun addPath(src: VectorPath, matrix: Matrix) {
        path.addPath(src.path, matrix)
        // TODO: apply matrix
    }

    fun addPath(src: Path, dx: Float, dy: Float) {
        path.addPath(src, dx, dy)
        // TODO: apply dx, dy
    }

    fun lineTo(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    fun rLineTo(dx: Float, dy: Float) {
        path.rLineTo(dx, dy)
    }

    fun cubicTo(x1: Float,
                y1: Float,
                x2: Float,
                y2: Float,
                x3: Float,
                y3: Float) {
        path.cubicTo(x1, y1, x2, y2, x3, y3)
        commands.add(CubicTo(x1, y1, x2, y2, x3, y3))
    }

    fun rCubicTo(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float) {
        path.rCubicTo(x1, y1, x2, y2, x3, y3)
        commands.add(RCubicTo(x1, y1, x2, y2, x3, y3))
    }

    fun arcTo(left: Float,
              top: Float,
              right: Float,
              bottom: Float,
              startAngle: Float,
              sweepAngle: Float,
              forceMoveTo: Boolean) {
        path.arcTo(left, top, right, bottom, startAngle, sweepAngle, forceMoveTo)
        commands.add(ArcTo(left, top, right, bottom, startAngle, sweepAngle, forceMoveTo))
    }

    fun quadTo(x1: Float, y1: Float, x2: Float, y2: Float) {
        path.quadTo(x1, y1, x2, y2)
    }

    fun rQuadTo(dx1: Float, dy1: Float, dx2: Float, dy2: Float) {
        path.rQuadTo(dx1, dy1, dx2, dy2)
    }

    fun close() {
        path.close()
    }

    fun reset() {
        path.reset()
        commands.clear()
    }

    fun set(src: VectorPath) {
        path.set(src.path)
        commands = src.commands
    }

    fun offset(dx: Float, dy: Float) {
        path.offset(dx, dy)
    }

    fun offset(dx: Float, dy: Float, dst: Path?) {
        path.offset(dx, dy, dst)
    }

    fun setLastPoint(dx: Float, dy: Float) {
        path.setLastPoint(dx, dy)
    }

    fun transform(matrix: Matrix) {
        path.transform(matrix)
    }

    fun transform(matrix: Matrix, dst: Path?) {
        path.transform(matrix, dst)
    }

    fun addPath(src: Path) {
        path.addPath(src)
    }

    fun addArc(oval: RectF, startAngle: Float, sweepAngle: Float) {
        path.addArc(oval, startAngle, sweepAngle)
    }

    fun addOval(oval: RectF, dir: Path.Direction) {
        path.addOval(oval, dir)
    }

    fun addRect(rect: RectF, dir: Path.Direction) {
        path.addRect(rect, dir)
    }

    fun addPath(src: Path, matrix: Matrix) {
        path.addPath(src, matrix)
    }

    fun addRoundRect(rect: RectF, radii: FloatArray, dir: Path.Direction) {
        path.addRoundRect(rect, radii, dir)
    }

    fun addRoundRect(rect: RectF, rx: Float, ry: Float, dir: Path.Direction) {
        path.addRoundRect(rect, rx, ry, dir)
    }

}