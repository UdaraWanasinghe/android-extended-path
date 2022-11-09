package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class LineTo(
    private val x: Float,
    private val y: Float
) : Command {

    override fun execute(path: Path) {
        path.lineTo(x, y)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): LineTo {
            return Json.decodeFromString(json)
        }
    }

}