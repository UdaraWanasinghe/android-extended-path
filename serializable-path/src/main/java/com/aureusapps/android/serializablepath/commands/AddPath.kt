package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.SerializablePath

internal class AddPath(
    private val path: SerializablePath
) : Command {

    override fun execute(path: Path) {
        path.addPath(this.path.path)
    }

    override fun toPathData(): String {
        return path.toPathData()
    }

    override val isClosed: Boolean get() = path.isClosed ?: false

}