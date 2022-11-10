package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.MatrixAsFloatArray
import com.aureusapps.android.serializablepath.SerializablePath
import kotlinx.serialization.Serializable

@Serializable
internal class Transform2(
    private val matrix: MatrixAsFloatArray,
    private val path: SerializablePath
) : PathCommand {

    override fun execute(path: Path) {
        path.transform(matrix, this.path.path)
    }

}