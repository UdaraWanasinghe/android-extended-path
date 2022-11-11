package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.SerializablePath
import com.aureusapps.android.serializablepath.serializers.MatrixAsFloatArray
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