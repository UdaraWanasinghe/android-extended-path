package com.aureusapps.android.serializablepath.commands

import android.graphics.Path

internal class LineTo(
    private val x: Float,
    private val y: Float,
    private val moveToOrigin: Boolean
) : Command {

    override fun execute(path: Path) {
        path.lineTo(x, y)
    }

    override fun toPathData(): String {
        return if (moveToOrigin) {
            "M0,0L$x,$y"
        } else {
            "L$x,$y"
        }
    }

    override val isClosed: Boolean = false


}