package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface PathCommand {
    fun execute(path: Path)
}