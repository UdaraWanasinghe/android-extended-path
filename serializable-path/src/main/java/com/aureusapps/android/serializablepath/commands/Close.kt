package com.aureusapps.android.serializablepath.commands

import android.graphics.Path

internal class Close : Command {

    override fun execute(path: Path) {
        path.close()
    }

    override fun toPathData(): String {
        return "Z"
    }

    override fun isClosed(): Boolean = true

}