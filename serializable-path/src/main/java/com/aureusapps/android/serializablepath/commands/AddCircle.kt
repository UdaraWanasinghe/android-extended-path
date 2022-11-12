package com.aureusapps.android.serializablepath.commands

import android.graphics.Path

internal class AddCircle(
    private val x: Float,
    private val y: Float,
    private val radius: Float,
    private val dir: Path.Direction
) : Command {

    override fun execute(path: Path) {
        path.addCircle(x, y, radius, dir)
    }

    override fun toPathData(): String {
        return if (dir == Path.Direction.CW) {
            "M${x + radius},${y}" +
                    "A$radius,$radius,0,0,0,$x,${y + radius}" +
                    "A$radius,$radius,0,0,0,${x - radius},$y" +
                    "A$radius,$radius,0,0,0,$x,${y - radius}" +
                    "A$radius,$radius,0,0,0,${x + radius},$y" +
                    "Z"
        } else {
            "M${x + radius},$y" +
                    "A$radius,$radius,0,0,0,$x,${y - radius}" +
                    "A$radius,$radius,0,0,0,${x - radius},$y" +
                    "A$radius,$radius,0,0,0,$x,${y + radius}" +
                    "A$radius,$radius,0,0,0,${x + radius},$y" +
                    "Z"
        }
    }

    override fun isClosed(): Boolean = true

}