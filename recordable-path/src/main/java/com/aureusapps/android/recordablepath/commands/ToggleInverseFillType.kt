package com.aureusapps.android.recordablepath.commands

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class ToggleInverseFillType : Command {

    override fun execute(path: android.graphics.Path) {
        path.toggleInverseFillType()
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): ToggleInverseFillType {
            return Json.decodeFromString(json)
        }
    }
    
}