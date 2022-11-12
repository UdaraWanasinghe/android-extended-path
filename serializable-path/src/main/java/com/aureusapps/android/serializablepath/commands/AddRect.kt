package com.aureusapps.android.serializablepath.commands

import android.graphics.Path

internal class AddRect(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val dir: Path.Direction
) : Command {

    override fun execute(path: Path) {
        path.addRect(left, top, right, bottom, dir)
    }

    override fun toPathData(): String {
        // there is no specific path command to draw a rectangle
        // we can break down this command to draw lines instead
        // we have to consider the direction of the rectangle
        return if (dir == Path.Direction.CW) {
            "M$left,${top}" +
                    "L$right,${top}" +
                    "L$right,${bottom}" +
                    "L$left,${bottom}" +
                    "L$left,${top}" +
                    "Z"
        } else {
            "M$left,${top}" +
                    "L$left,${bottom}" +
                    "L$right,${bottom}" +
                    "L$right,${top}" +
                    "L$left,${top}" +
                    "Z"
        }
    }

}