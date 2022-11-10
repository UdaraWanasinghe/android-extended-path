package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.MatrixAsFloatArray
import kotlinx.serialization.Serializable

@Serializable
internal class Transform1(
    private val matrix: MatrixAsFloatArray
) : PathCommand {

    override fun execute(path: Path) {
        path.transform(matrix)
    }

}