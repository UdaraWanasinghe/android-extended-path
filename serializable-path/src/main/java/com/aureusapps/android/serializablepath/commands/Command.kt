package com.aureusapps.android.serializablepath.commands

import android.graphics.Path

interface Command {
    fun execute(path: Path)
    fun toPathData(): String
    val isClosed: Boolean
}