package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class RQuadTo(
    private val dx1: Float,
    private val dy1: Float,
    private val dx2: Float,
    private val dy2: Float
) : PathCommand {

    override fun execute(path: Path) {
        path.rQuadTo(dx1, dy1, dx2, dy2)
    }

}