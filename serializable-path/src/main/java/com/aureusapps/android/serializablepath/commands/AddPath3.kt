package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.SerializablePath
import kotlinx.serialization.Serializable

@Serializable
internal class AddPath3(
    private val path: SerializablePath,
    private val dx: Float,
    private val dy: Float
) : PathCommand {

    override fun execute(path: Path) {
        path.addPath(this.path.path, dx, dy)
    }

}