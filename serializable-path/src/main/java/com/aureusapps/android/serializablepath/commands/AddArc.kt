package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

internal class AddArc(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val startAngle: Float,
    private val sweepAngle: Float
) : Command {

    override fun execute(path: Path) {
        path.addArc(left, top, right, bottom, startAngle, sweepAngle)
    }

    override fun toPathData(): String {
        val cx = (left + right) / 2f
        val cy = (top + bottom) / 2f
        val a = (right - left) / 2f
        val b = (bottom - top) / 2f
        val sa = startAngle.toRadians
        val aa = (startAngle + sweepAngle).toRadians
        var acs = a * sin(sa)
        var bss = b * cos(sa)
        var r = a * b / sqrt(acs * acs + bss * bss)
        val sx = cx + r * cos(sa)
        val sy = cy + r * sin(sa)
        acs = a * sin(aa)
        bss = b * cos(aa)
        r = a * b / sqrt(acs * acs + bss * bss)
        val ex = cx + r * cos(aa)
        val ey = cy + r * sin(aa)
        return "M$sx,${sy}A$a,$b,0,0,0,$ex,${ey}Z"
    }

    override fun isClosed(): Boolean = true

    private val Float.toRadians get() = this * Math.PI / 180f

}