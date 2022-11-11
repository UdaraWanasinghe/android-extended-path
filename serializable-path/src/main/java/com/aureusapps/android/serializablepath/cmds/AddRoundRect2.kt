package com.aureusapps.android.serializablepath.cmds

import android.graphics.Path

internal class AddRoundRect2(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val rx: Float,
    private val ry: Float,
    private val dir: Path.Direction
) : Command {
    override fun execute(path: Path) {
        path.addRoundRect(left, top, right, bottom, rx, ry, dir)
    }

    override fun toPathData(): String {
        return if (dir == Path.Direction.CW) {
            "M$left,${top + ry}" +
                    "A$rx,$ry,0,0,0,${left + rx},${top}" +
                    "L${right - rx},${top}" +
                    "A$rx,$ry,0,0,0,$right,${top + ry}" +
                    "L$right,${bottom - ry}" +
                    "A$rx,$ry,0,0,0,${right - rx},${bottom}" +
                    "L${left + rx},${bottom}" +
                    "A$rx,$ry,0,0,0,$left,${bottom - ry}" +
                    "L$left,${top + ry}" +
                    "Z"
        } else {
            "M${left + rx},$top" +
                    "A$rx,$ry,0,0,0,$left,${top + ry}" +
                    "L$left,${bottom - ry}" +
                    "A$rx,$ry,0,0,0,${left + rx},${bottom}" +
                    "L${right - rx},${bottom}" +
                    "A$rx,$ry,0,0,0,$right,${bottom - ry}" +
                    "L$right,${top + ry}" +
                    "A$rx,$ry,0,0,0,${right - rx},${top}" +
                    "L${left + rx},$top" +
                    "Z"
        }
    }
}