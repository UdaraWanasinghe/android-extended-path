package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Rewind")
internal object Rewind : Command {

    override fun execute(path: Path) {
        path.rewind()
    }

}