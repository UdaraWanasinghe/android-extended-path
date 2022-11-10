package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class Offset(
    private val dx: Float,
    private val dy: Float
) : Command {

    override fun execute(path: Path) {
        path.offset(dx, dy)
    }

}