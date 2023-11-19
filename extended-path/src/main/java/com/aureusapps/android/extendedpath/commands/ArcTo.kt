package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ArcTo")
internal data class ArcTo(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val startAngle: Float,
    private val sweepAngle: Float,
    private val forceMoveTo: Boolean
) : Command {

    override fun execute(path: Path) {
        path.arcTo(left, top, right, bottom, startAngle, sweepAngle, forceMoveTo)
    }

}