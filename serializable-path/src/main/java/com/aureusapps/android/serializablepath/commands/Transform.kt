package com.aureusapps.android.serializablepath.commands

import android.graphics.Matrix
import android.graphics.Path

class Transform(
    private val matrix: Matrix,
    override val isClosed: Boolean
) : Command {

    override fun execute(path: Path) {
        path.transform(matrix)
    }

    override fun toPathData(): String {
        return ""
    }

}