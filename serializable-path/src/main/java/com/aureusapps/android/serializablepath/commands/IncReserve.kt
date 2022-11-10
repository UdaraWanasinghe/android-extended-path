package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class IncReserve(
    private val extraPtCount: Int
) : PathCommand {

    override fun execute(path: Path) {
        path.incReserve(extraPtCount)
    }

}