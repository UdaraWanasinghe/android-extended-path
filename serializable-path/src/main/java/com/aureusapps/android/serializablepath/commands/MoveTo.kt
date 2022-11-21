package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("MoveTo")
internal class MoveTo(
    private val x: Float,
    private val y: Float
) : Command {

    override fun execute(path: Path) {
        path.moveTo(x, y)
    }

}