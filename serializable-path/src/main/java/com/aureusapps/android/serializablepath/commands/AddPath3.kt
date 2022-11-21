package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.ExtendedPath
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AddPath3")
internal class AddPath3(
    private val path: ExtendedPath,
    private val dx: Float,
    private val dy: Float
) : Command {

    override fun execute(path: Path) {
        path.addPath(this.path, dx, dy)
    }

}