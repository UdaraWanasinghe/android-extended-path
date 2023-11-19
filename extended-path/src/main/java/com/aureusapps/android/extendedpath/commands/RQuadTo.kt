package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RQuadTo")
internal data class RQuadTo(
    private val dx1: Float,
    private val dy1: Float,
    private val dx2: Float,
    private val dy2: Float
) : Command {

    override fun execute(path: Path) {
        path.rQuadTo(dx1, dy1, dx2, dy2)
    }

}