package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import com.aureusapps.android.recordablepath.MatrixAsFloatArray
import com.aureusapps.android.recordablepath.RecordablePath
import kotlinx.serialization.Serializable

@Serializable
internal class AddPath2(
    private val path: RecordablePath,
    private val matrix: MatrixAsFloatArray
) : PathCommand {

    override fun execute(path: Path) {
        path.addPath(this.path, matrix)
    }

}