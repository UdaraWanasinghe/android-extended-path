package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("MoveTo")
internal data class MoveTo(
    private val x: Float,
    private val y: Float
) : Command {

    override fun execute(path: Path) {
        path.moveTo(x, y)
    }

}