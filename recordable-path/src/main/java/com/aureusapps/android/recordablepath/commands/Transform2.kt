package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import com.aureusapps.android.recordablepath.MatrixAsFloatArray
import com.aureusapps.android.recordablepath.RecordablePath
import kotlinx.serialization.Serializable

@Serializable
internal class Transform2(
    private val matrix: MatrixAsFloatArray,
    private val path: RecordablePath
) : PathCommand {

    override fun execute(path: Path) {
        path.transform(matrix, this.path.path)
    }

}