package com.aureusapps.android.serializablepath.commands

import android.graphics.Path

internal class MoveTo(
    private val x: Float,
    private val y: Float
) : Command {

    override fun execute(path: Path) {
        path.moveTo(x, y)
    }

    override fun toPathData(): String {
        return "M$x,$y"
    }

    override val isClosed: Boolean = false

}