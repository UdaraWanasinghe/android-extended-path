package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import com.aureusapps.android.recordablepath.RecordablePath
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class AddPath3(
    private val path: RecordablePath,
    private val dx: Float,
    private val dy: Float
) : Command {

    override fun execute(path: Path) {
        path.addPath(this.path, dx, dy)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): AddPath3 {
            return Json.decodeFromString(json)
        }
    }

}