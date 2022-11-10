package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal object Rewind : Command {

    override fun execute(path: Path) {
        path.rewind()
    }

}