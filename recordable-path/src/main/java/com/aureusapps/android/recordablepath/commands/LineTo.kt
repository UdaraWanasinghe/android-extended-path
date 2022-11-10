package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class LineTo(
    private val x: Float,
    private val y: Float
) : PathCommand {

    override fun execute(path: Path) {
        path.lineTo(x, y)
    }

}