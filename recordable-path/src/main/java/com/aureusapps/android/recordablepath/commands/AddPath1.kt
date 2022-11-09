package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import com.aureusapps.android.recordablepath.RecordablePath
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class AddPath1(
    private val path: RecordablePath
) : Command {

    override fun execute(path: Path) {
        path.addPath(this.path)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): AddPath1 {
            return Json.decodeFromString(json)
        }
    }

}