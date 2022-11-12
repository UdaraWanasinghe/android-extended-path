package com.aureusapps.android.serializablepath.cmds

import android.graphics.Path

internal class RCubicTo(
    private val x1: Float,
    private val y1: Float,
    private val x2: Float,
    private val y2: Float,
    private val x3: Float,
    private val y3: Float
) : Command {

    override fun execute(path: Path) {
        path.cubicTo(x1, y1, x2, y2, x3, y3)
    }

    override fun toPathData(): String {
        return "c$x1,$y1,$x2,$y2,$x3,$y3"
    }

}