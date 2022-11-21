package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AddCircle")
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