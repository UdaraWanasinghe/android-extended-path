package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class QuadTo(
    private val x1: Float,
    private val y1: Float,
    private val x2: Float,
    private val y2: Float
) : PathCommand {

    override fun execute(path: Path) {
        path.quadTo(x1, y1, x2, y2)
    }

}