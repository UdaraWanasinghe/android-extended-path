package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import com.aureusapps.android.recordablepath.RecordablePath
import kotlinx.serialization.Serializable

@Serializable
internal class AddPath3(
    private val path: RecordablePath,
    private val dx: Float,
    private val dy: Float
) : Command {

    override fun execute(path: Path) {
        path.addPath(this.path, dx, dy)
    }

}