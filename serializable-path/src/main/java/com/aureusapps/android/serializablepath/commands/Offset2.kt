package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.ExtendedPath
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Offset")
internal class Offset2(
    private val dx: Float,
    private val dy: Float,
    private val dst: ExtendedPath?
) : PathCommand {

    override fun execute(path: Path) {
        path.offset(dx, dy, dst)
    }

}