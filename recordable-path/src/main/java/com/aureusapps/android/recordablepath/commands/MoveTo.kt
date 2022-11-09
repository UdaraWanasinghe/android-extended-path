package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class MoveTo(
    private val x: Float,
    private val y: Float
) : Command {

    override fun execute(path: Path) {
        path.moveTo(x, y)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): MoveTo {
            return Json.decodeFromString(json)
        }
    }

}