package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import org.json.JSONObject

internal interface Command {
    fun execute(path: Path)
    fun toJson(): String
}