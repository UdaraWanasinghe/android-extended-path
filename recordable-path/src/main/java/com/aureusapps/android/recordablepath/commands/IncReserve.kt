package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal class IncReserve(
    private val extraPtCount: Int
) : Command {

    override fun execute(path: Path) {
        path.incReserve(extraPtCount)
    }

}