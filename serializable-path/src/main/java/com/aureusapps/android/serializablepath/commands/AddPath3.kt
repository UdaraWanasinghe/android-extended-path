package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.RecordablePath
import kotlinx.serialization.Serializable

@Serializable
internal class AddPath3(
    private val path: RecordablePath,
    private val dx: Float,
    private val dy: Float
) : PathCommand {

    override fun execute(path: Path) {
        path.addPath(this.path.path, dx, dy)
    }

}