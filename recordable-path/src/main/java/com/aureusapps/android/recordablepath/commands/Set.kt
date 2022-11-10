package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import com.aureusapps.android.recordablepath.RecordablePath
import kotlinx.serialization.Serializable

@Serializable
internal class Set(
    private val path: RecordablePath
) : Command {

    override fun execute(path: Path) {
        path.set(this.path)
    }

}