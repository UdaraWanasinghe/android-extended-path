package com.aureusapps.android.extendedpath.commands

import android.graphics.Matrix
import android.graphics.Path
import com.aureusapps.android.extendedpath.serializers.MatrixAsFloatArray
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Transform1")
internal data class Transform1(
    private val matrix: MatrixAsFloatArray
) : Command {

    override fun execute(path: Path) {
        path.transform(matrix)
    }

    fun concat(matrix: Matrix) {
        this.matrix.postConcat(matrix)
    }

}