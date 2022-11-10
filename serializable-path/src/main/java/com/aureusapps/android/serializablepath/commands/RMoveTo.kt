package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class RMoveTo(
    private val dx: Float,
    private val dy: Float
) : PathCommand {

    override fun execute(path: Path) {
        path.rMoveTo(dx, dy)
    }

}