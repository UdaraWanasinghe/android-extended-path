package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("LineTo")
internal data class LineTo(
    private val x: Float,
    private val y: Float
) : Command {

    override fun execute(path: Path) {
        path.lineTo(x, y)
    }

}