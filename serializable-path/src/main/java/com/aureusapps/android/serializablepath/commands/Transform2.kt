package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.ExtendedPath
import com.aureusapps.android.serializablepath.serializers.MatrixAsFloatArray
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Transform")
internal class Transform2(
    private val matrix: MatrixAsFloatArray,
    private val dst: ExtendedPath?
) : Command {

    override fun execute(path: Path) {
        path.transform(matrix, dst)
    }

}