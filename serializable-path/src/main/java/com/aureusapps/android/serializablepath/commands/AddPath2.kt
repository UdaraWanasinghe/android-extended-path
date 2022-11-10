package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.MatrixAsFloatArray
import com.aureusapps.android.serializablepath.SerializablePath
import kotlinx.serialization.Serializable

@Serializable
internal class AddPath2(
    private val path: SerializablePath,
    private val matrix: MatrixAsFloatArray
) : PathCommand {

    override fun execute(path: Path) {
        path.addPath(this.path.path, matrix)
    }

}