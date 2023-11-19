package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import com.aureusapps.android.extendedpath.ExtendedPath
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AddPath1")
internal data class AddPath1(
    private val path: ExtendedPath
) : Command {

    override fun execute(path: Path) {
        path.addPath(this.path)
    }

}