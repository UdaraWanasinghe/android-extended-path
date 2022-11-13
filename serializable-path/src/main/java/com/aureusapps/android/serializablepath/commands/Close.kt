package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Close")
internal object Close : PathCommand {

    override fun execute(path: Path) {
        path.close()
    }

}