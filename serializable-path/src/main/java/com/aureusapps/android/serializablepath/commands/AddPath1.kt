package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.RecordablePath
import kotlinx.serialization.Serializable

@Serializable
internal class AddPath1(
    private val path: RecordablePath
) : PathCommand {

    override fun execute(path: Path) {
        path.addPath(this.path.path)
    }

}