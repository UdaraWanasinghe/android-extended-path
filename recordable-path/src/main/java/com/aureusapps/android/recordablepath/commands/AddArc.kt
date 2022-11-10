package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class AddArc(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val startAngle: Float,
    private val sweepAngle: Float
) : PathCommand {

    override fun execute(path: Path) {
        path.addArc(left, top, right, bottom, startAngle, sweepAngle)
    }

}