package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class AddRoundRect1(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val radii: FloatArray,
    private val dir: Path.Direction
) : PathCommand {

    override fun execute(path: Path) {
        path.addRoundRect(left, top, right, bottom, radii, dir)
    }

}