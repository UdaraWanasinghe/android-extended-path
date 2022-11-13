package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AddRect")
internal class AddRect(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val dir: Path.Direction
) : PathCommand {

    override fun execute(path: Path) {
        path.addRect(left, top, right, bottom, dir)
    }

}