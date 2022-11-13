package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Rewind")
internal object Rewind : PathCommand {

    override fun execute(path: Path) {
        path.rewind()
    }

}