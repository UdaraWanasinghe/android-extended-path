package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface PathCommand {
    fun execute(path: Path)
}