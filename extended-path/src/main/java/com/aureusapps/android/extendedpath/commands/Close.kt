package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Close")
internal data object Close : Command {

    override fun execute(path: Path) {
        path.close()
    }

}