package com.aureusapps.android.serializablepath.commands

import android.graphics.Path

internal class AddOval(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val dir: Path.Direction
) : Command {

    override fun execute(path: Path) {
        path.addOval(left, top, right, bottom, dir)
    }

    override fun toPathData(): String {
        val rx = (right - left) / 2f
        val ry = (bottom - top) / 2f
        val cx = left + rx
        val cy = top + ry
        return if (dir == Path.Direction.CW) {
            "M$right,$cy" +
                    "A$rx,$ry,0,0,0,$cx,$bottom" +
                    "A$rx,$ry,0,0,0,$left,$cy" +
                    "A$rx,$ry,0,0,0,$cx,$top" +
                    "A$rx,$ry,0,0,0,$right,$cy" +
                    "Z"
        } else {
            "M$right,$cy" +
                    "A$rx,$ry,0,0,0,$cx,$top" +
                    "A$rx,$ry,0,0,0,$left,$cy" +
                    "A$rx,$ry,0,0,0,$cx,$bottom" +
                    "A$rx,$ry,0,0,0,$right,$cy" +
                    "Z"
        }
    }
    
    override fun isClosed(): Boolean = true

}