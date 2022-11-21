package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Offset")
internal class Offset1(
    private val dx: Float,
    private val dy: Float
) : PathCommand {

    override fun execute(path: Path) {
        path.offset(dx, dy)
    }

}