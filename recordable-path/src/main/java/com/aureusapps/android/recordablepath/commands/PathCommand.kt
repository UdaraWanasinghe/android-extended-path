package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
sealed interface PathCommand {
    fun execute(path: Path)
}