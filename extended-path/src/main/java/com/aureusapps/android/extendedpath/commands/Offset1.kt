package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Offset1")
internal class Offset1(
    private val dx: Float,
    private val dy: Float
) : Command {

    override fun execute(path: Path) {
        path.offset(dx, dy)
    }

}