package com.aureusapps.android.serializablepath.cmds

import android.graphics.Path

interface Command {
    fun execute(path: Path)
    fun toPathData(): String
}