package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class AddCircle(
    private val x: Float,
    private val y: Float,
    private val radius: Float,
    private val dir: Path.Direction
) : Command {

    override fun execute(path: Path) {
        path.addCircle(x, y, radius, dir)
    }

}