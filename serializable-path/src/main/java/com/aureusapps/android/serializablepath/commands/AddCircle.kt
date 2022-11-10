package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class AddCircle(
    private val x: Float,
    private val y: Float,
    private val radius: Float,
    private val dir: Path.Direction
) : PathCommand {

    override fun execute(path: Path) {
        path.addCircle(x, y, radius, dir)
    }

}