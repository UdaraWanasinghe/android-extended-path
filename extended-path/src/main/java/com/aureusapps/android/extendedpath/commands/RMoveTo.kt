package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RMoveTo")
internal data class RMoveTo(
    private val dx: Float,
    private val dy: Float
) : Command {

    override fun execute(path: Path) {
        path.rMoveTo(dx, dy)
    }

}