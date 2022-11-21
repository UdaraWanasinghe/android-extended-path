package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("IncReserve")
internal class IncReserve(
    private val extraPtCount: Int
) : Command {

    override fun execute(path: Path) {
        path.incReserve(extraPtCount)
    }

}