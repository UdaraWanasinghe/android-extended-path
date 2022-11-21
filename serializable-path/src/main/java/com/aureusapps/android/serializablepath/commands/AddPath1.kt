package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.ExtendedPath
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AddPath1")
internal class AddPath1(
    private val path: ExtendedPath
) : PathCommand {

    override fun execute(path: Path) {
        path.addPath(this.path)
    }

}