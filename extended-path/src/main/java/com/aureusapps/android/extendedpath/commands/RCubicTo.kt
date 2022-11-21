package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RCubicTo")
internal class RCubicTo(
    private val x1: Float,
    private val y1: Float,
    private val x2: Float,
    private val y2: Float,
    private val x3: Float,
    private val y3: Float
) : Command {

    override fun execute(path: Path) {
        path.rCubicTo(x1, y1, x2, y2, x3, y3)
    }

}