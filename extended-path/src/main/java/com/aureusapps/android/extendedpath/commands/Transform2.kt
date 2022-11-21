package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import com.aureusapps.android.extendedpath.ExtendedPath
import com.aureusapps.android.extendedpath.serializers.MatrixAsFloatArray
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Transform2")
internal class Transform2(
    private val matrix: MatrixAsFloatArray,
    private val dst: ExtendedPath?
) : Command {

    override fun execute(path: Path) {
        path.transform(matrix, dst)
    }

}