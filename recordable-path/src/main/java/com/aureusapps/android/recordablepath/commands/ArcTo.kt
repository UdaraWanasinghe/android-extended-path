package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class ArcTo(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val startAngle: Float,
    private val sweepAngle: Float,
    private val forceMoveTo: Boolean
) : Command {

    override fun execute(path: Path) {
        path.arcTo(left, top, right, bottom, startAngle, sweepAngle, forceMoveTo)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): ArcTo {
            return Json.decodeFromString(json)
        }
    }

}