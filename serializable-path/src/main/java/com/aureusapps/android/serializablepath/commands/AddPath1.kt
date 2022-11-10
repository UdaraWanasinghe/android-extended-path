package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.SerializablePath
import kotlinx.serialization.Serializable

@Serializable
internal class AddPath1(
    private val path: SerializablePath
) : PathCommand {

    override fun execute(path: Path) {
        path.addPath(this.path.path)
    }

}