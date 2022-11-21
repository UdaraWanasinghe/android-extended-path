package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable

@Serializable
sealed interface Command {
    fun execute(path: Path)
}