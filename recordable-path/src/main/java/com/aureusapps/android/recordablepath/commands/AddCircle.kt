package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class AddCircle(
    private val x: Float,
    private val y: Float,
    private val radius: Float,
    private val dir: Path.Direction
) : Command {

    override fun execute(path: Path) {
        path.addCircle(x, y, radius, dir)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): AddCircle {
            return Json.decodeFromString(json)
        }
    }

}