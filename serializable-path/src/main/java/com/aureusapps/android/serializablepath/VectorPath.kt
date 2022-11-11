package com.aureusapps.android.serializablepath

import android.graphics.Path
import com.aureusapps.android.serializablepath.cmds.AddRect
import com.aureusapps.android.serializablepath.cmds.Command
import com.aureusapps.android.serializablepath.cmds.MoveTo
import com.aureusapps.android.serializablepath.cmds.RMoveTo

class VectorPath : Path() {

    companion object {
        fun fromPathData(pathData: String): Path {

            return Path()
        }
    }

    var commands = mutableListOf<Command>()

    fun toPathData(): String {
        return commands.joinToString(separator = "") { command -> command.toPathData() }
    }

    override fun moveTo(x: Float, y: Float) {
        super.moveTo(x, y)
        commands.add(MoveTo(x, y))
    }

    override fun rMoveTo(dx: Float, dy: Float) {
        super.rMoveTo(dx, dy)
        commands.add(RMoveTo(dx, dy))
    }

    override fun addRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        dir: Direction
    ) {
        super.addRect(left, top, right, bottom, dir)
        commands.add(AddRect(left, top, right, bottom, dir))
    }

    override fun addRoundRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        radii: FloatArray,
        dir: Direction
    ) {
        super.addRoundRect(left, top, right, bottom, radii, dir)
    }

    override fun addRoundRect(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        rx: Float,
        ry: Float,
        dir: Direction
    ) {
        super.addRoundRect(left, top, right, bottom, rx, ry, dir)
    }

}