package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RLineTo")
internal class RLineTo(
    private val dx: Float,
    private val dy: Float
) : PathCommand {

    override fun execute(path: Path) {
        path.rLineTo(dx, dy)
    }

}