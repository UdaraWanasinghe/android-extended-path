package com.aureusapps.android.recordablepath.commands

import android.graphics.Path

sealed interface Command {
    fun execute(path: Path)
}