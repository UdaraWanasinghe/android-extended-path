package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.serializers.MatrixAsFloatArray
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Transform")
internal class Transform1(
    private val matrix: MatrixAsFloatArray
) : Command {

    override fun execute(path: Path) {
        path.transform(matrix)
    }

}