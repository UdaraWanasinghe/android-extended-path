package com.aureusapps.android.serializablepath.commands

import android.graphics.Path

internal class RMoveTo(
    private val dx: Float,
    private val dy: Float
) : Command {

    override fun execute(path: Path) {
        path.rMoveTo(dx, dy)
    }

    override fun toPathData(): String {
        return "m$dx,$dy"
    }

    override fun isClosed(): Boolean = false

}