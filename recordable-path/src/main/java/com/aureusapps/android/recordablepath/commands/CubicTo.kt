package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class CubicTo(
    private val x1: Float,
    private val y1: Float,
    private val x2: Float,
    private val y2: Float,
    private val x3: Float,
    private val y3: Float
) : Command {

    override fun execute(path: Path) {
        path.cubicTo(x1, y1, x2, y2, x3, y3)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): CubicTo {
            return Json.decodeFromString(json)
        }
    }

}