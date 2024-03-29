package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("QuadTo")
internal data class QuadTo(
    private val x1: Float,
    private val y1: Float,
    private val x2: Float,
    private val y2: Float
) : Command {

    override fun execute(path: Path) {
        path.quadTo(x1, y1, x2, y2)
    }

}