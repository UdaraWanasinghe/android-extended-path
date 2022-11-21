package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import com.aureusapps.android.extendedpath.ExtendedPath
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Offset2")
internal class Offset2(
    private val dx: Float,
    private val dy: Float,
    private val dst: ExtendedPath?
) : Command {

    override fun execute(path: Path) {
        path.offset(dx, dy, dst)
    }

}