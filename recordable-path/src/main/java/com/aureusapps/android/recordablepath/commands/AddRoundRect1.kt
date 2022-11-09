package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class AddRoundRect1(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val radii: FloatArray,
    private val dir: Path.Direction
) : Command {

    override fun execute(path: Path) {
        path.addRoundRect(left, top, right, bottom, radii, dir)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): AddRoundRect1 {
            return Json.decodeFromString(json)
        }
    }

}