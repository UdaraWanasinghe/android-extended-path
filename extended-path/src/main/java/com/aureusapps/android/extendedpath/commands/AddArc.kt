package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AddArc")
internal class AddArc(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val startAngle: Float,
    private val sweepAngle: Float
) : Command {

    override fun execute(path: Path) {
        path.addArc(left, top, right, bottom, startAngle, sweepAngle)
    }

}