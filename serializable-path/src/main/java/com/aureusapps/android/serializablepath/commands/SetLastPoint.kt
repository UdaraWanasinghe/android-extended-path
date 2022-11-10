package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class SetLastPoint(
    private val x: Float,
    private val y: Float
) : PathCommand {

    override fun execute(path: Path) {
        path.setLastPoint(x, y)
    }

}