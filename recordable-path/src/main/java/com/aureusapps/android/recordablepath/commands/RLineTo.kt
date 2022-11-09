package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class RLineTo(
    private val dx: Float,
    private val dy: Float
) : Command {

    override fun execute(path: Path) {
        path.rLineTo(dx, dy)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): RLineTo {
            return Json.decodeFromString(json)
        }
    }

}