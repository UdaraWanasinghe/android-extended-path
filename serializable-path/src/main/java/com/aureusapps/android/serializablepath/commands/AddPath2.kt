package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.SerializablePath
import com.aureusapps.android.serializablepath.serializers.MatrixAsFloatArray
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AddPath2")
internal class AddPath2(
    private val path: SerializablePath,
    private val matrix: MatrixAsFloatArray
) : PathCommand {

    override fun execute(path: Path) {
        path.addPath(this.path.path, matrix)
    }

}