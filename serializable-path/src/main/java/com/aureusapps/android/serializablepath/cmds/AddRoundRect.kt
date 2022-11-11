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
        val rx0 = radii[0]
        val ry0 = radii[1]
        val rx1 = radii[2]
        val ry1 = radii[3]
        val rx2 = radii[4]
        val ry2 = radii[5]
        val rx3 = radii[6]
        val ry3 = radii[7]
        return if (dir == Path.Direction.CW) {
            "M$left,${top + ry0}" +
                    "A$rx0,$ry0,0,0,0,${left + rx0},${top}" +
                    "L${right - rx1},${top}" +
                    "A$rx1,$ry1,0,0,0,$right,${top + ry1}" +
                    "L$right,${bottom - ry2}" +
                    "A$rx2,$ry2,0,0,0,${right - rx2},${bottom}" +
                    "L${left + rx3},${bottom}" +
                    "A$rx3,$ry3,0,0,0,$left,${bottom - ry3}" +
                    "L$left,${top + ry0}" +
                    "Z"
        } else {
            "M${left + rx0},$top" +
                    "A$rx0,$ry0,0,0,0,$left,${top + ry0}" +
                    "L$left,${bottom - ry3}" +
                    "A$rx3,$ry3,0,0,0,${left + rx3},${bottom}" +
                    "L${right - rx2},${bottom}" +
                    "A$rx2,$ry2,0,0,0,$right,${bottom - ry2}" +
                    "L$right,${top + ry1}" +
                    "A$rx1,$ry1,0,0,0,${right - rx1},${top}" +
                    "L${left + rx0},$top" +
                    "Z"
        }
    }
}