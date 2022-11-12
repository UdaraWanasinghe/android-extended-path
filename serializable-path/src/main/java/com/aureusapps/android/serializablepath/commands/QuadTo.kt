package com.aureusapps.android.serializablepath.commands

import android.graphics.Path

internal class QuadTo(
    private val x1: Float,
    private val y1: Float,
    private val x2: Float,
    private val y2: Float,
    private val moveToOrigin: Boolean
) : Command {

    override fun execute(path: Path) {
        path.quadTo(x1, y1, x2, y2)
    }

    override fun toPathData(): String {
        return if (moveToOrigin) {
            "M0,0" +
                    "Q$x1,$y1,$x2,$y2"
        } else {
            "Q$x1,$y1,$x2,$y2"
        }
    }

    override val isClosed: Boolean = false

}