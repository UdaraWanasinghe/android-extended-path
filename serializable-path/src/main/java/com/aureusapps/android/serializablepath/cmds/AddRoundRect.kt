package com.aureusapps.android.serializablepath.cmds

import android.graphics.Path

internal class AddRoundRect(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val radii: FloatArray,
    private val dir: Path.Direction
) : Command {
    override fun execute(path: Path) {
        path.addRoundRect(left, top, right, bottom, radii, dir)
    }

    override fun toPathData(): String {
        return if (dir == Path.Direction.CW) {
            ""
        } else {
            ""
        }
    }
}