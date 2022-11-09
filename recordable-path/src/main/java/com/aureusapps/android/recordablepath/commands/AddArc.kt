package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class AddArc(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val startAngle: Float,
    private val sweepAngle: Float
) : Command {

    override fun execute(path: Path) {
        path.addArc(left, top, right, bottom, startAngle, sweepAngle)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): AddArc {
            return Json.decodeFromString(json)
        }
    }

}