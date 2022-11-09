package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class IncReserve(
    private val extraPtCount: Int
) : Command {

    override fun execute(path: Path) {
        path.incReserve(extraPtCount)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): IncReserve {
            return Json.decodeFromString(json)
        }
    }

}