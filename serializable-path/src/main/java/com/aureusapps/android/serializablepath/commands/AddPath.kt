package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.VectorPath

internal class AddPath(
    private val path: VectorPath
) : Command {

    override fun execute(path: Path) {
        path.addPath(this.path.path)
    }

    override fun toPathData(): String {
        return path.toPathData()
    }

    override fun isClosed(): Boolean = path.isClosed()

}