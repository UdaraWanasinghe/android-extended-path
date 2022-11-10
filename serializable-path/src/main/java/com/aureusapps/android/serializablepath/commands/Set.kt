package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import com.aureusapps.android.serializablepath.RecordablePath
import kotlinx.serialization.Serializable

@Serializable
internal class Set(
    private val path: RecordablePath
) : PathCommand {

    override fun execute(path: Path) {
        path.set(this.path.path)
    }

}