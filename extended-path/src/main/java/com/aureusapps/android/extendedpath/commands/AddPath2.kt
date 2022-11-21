package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import com.aureusapps.android.extendedpath.ExtendedPath
import com.aureusapps.android.extendedpath.serializers.MatrixAsFloatArray
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AddPath2")
internal class AddPath2(
    private val path: ExtendedPath,
    private val matrix: MatrixAsFloatArray
) : Command {

    override fun execute(path: Path) {
        path.addPath(this.path, matrix)
    }

}