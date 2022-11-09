package com.aureusapps.android.recordablepath

import android.graphics.Matrix
import android.graphics.Path
import com.aureusapps.android.recordablepath.commands.*
import kotlinx.serialization.Serializable

@Serializable
class RecordablePath : Path() {

    private val commands = mutableListOf<Command>()

    override fun close() {
        super.close()
        commands.add(Close())
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

    override fun cubicTo(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float) {
        super.cubicTo(x1, y1, x2, y2, x3, y3)
        commands.add(CubicTo(x1, y1, x2, y2, x3, y3))
    }

    override fun rCubicTo(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float) {
        super.rCubicTo(x1, y1, x2, y2, x3, y3)
        commands.add(RCubicTo(x1, y1, x2, y2, x3, y3))
    }

    override fun addCircle(x: Float, y: Float, radius: Float, dir: Direction) {
        super.addCircle(x, y, radius, dir)
        commands.add(AddCircle(x, y, radius, dir))
    }

    override fun reset() {
        super.reset()
        commands.clear()
    }

    override fun addRect(left: Float, top: Float, right: Float, bottom: Float, dir: Direction) {
        super.addRect(left, top, right, bottom, dir)
        commands.add(AddRect(left, top, right, bottom, dir))
    }

    override fun addRoundRect(left: Float, top: Float, right: Float, bottom: Float, radii: FloatArray, dir: Direction) {
        super.addRoundRect(left, top, right, bottom, radii, dir)
        commands.add(AddRoundRect1(left, top, right, bottom, radii, dir))
    }

    override fun addRoundRect(left: Float, top: Float, right: Float, bottom: Float, rx: Float, ry: Float, dir: Direction) {
        super.addRoundRect(left, top, right, bottom, rx, ry, dir)
        commands.add(AddRoundRect2(left, top, right, bottom, rx, ry, dir))
    }

    override fun addArc(left: Float, top: Float, right: Float, bottom: Float, startAngle: Float, sweepAngle: Float) {
        super.addArc(left, top, right, bottom, startAngle, sweepAngle)
        commands.add(AddArc(left, top, right, bottom, startAngle, sweepAngle))
    }

    override fun addOval(left: Float, top: Float, right: Float, bottom: Float, dir: Direction) {
        super.addOval(left, top, right, bottom, dir)
        commands.add(AddOval(left, top, right, bottom, dir))
    }

    fun addPath(src: RecordablePath) {
        super.addPath(src)
        commands.add(AddPath1(src))
    }

    override fun addPath(src: Path, matrix: Matrix) {
        super.addPath(src, matrix)
    }

    override fun addPath(src: Path, dx: Float, dy: Float) {
        super.addPath(src, dx, dy)
    }

    override fun rewind() {
        super.rewind()
    }

    override fun incReserve(extraPtCount: Int) {
        super.incReserve(extraPtCount)
    }

    override fun toggleInverseFillType() {
        super.toggleInverseFillType()
    }

    override fun arcTo(left: Float, top: Float, right: Float, bottom: Float, startAngle: Float, sweepAngle: Float, forceMoveTo: Boolean) {
        super.arcTo(left, top, right, bottom, startAngle, sweepAngle, forceMoveTo)
    }

    override fun offset(dx: Float, dy: Float) {
        super.offset(dx, dy)
    }

    override fun offset(dx: Float, dy: Float, dst: Path?) {
        super.offset(dx, dy, dst)
    }

    override fun quadTo(x1: Float, y1: Float, x2: Float, y2: Float) {
        super.quadTo(x1, y1, x2, y2)
    }

    override fun setFillType(ft: FillType) {
        super.setFillType(ft)
    }

    override fun rQuadTo(dx1: Float, dy1: Float, dx2: Float, dy2: Float) {
        super.rQuadTo(dx1, dy1, dx2, dy2)
    }

    override fun setLastPoint(dx: Float, dy: Float) {
        super.setLastPoint(dx, dy)
    }

    override fun transform(matrix: Matrix, dst: Path?) {
        super.transform(matrix, dst)
    }

    fun set(src: RecordablePath) {
        super.set(src)
    }

    override fun transform(matrix: Matrix) {
        super.transform(matrix)
    }

}