package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AddRoundRect2")
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

}