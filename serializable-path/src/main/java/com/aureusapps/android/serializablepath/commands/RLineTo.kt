package com.aureusapps.android.serializablepath.commands

import android.graphics.Path

internal class RLineTo(
    private val x: Float,
    private val y: Float,
    private val moveToOrigin: Boolean
) : Command {

    override fun execute(path: Path) {
        path.rLineTo(x, y)
    }

    override fun toPathData(): String {
        return if (moveToOrigin) {
            "M0,0l$x,$y"
        } else {
            "l$x,$y"
        }
    }

    override val isClosed: Boolean = false

}