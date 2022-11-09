package com.aureusapps.android.recordablepath.commands

import android.graphics.Matrix
import android.graphics.Path
import com.aureusapps.android.recordablepath.RecordablePath
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class AddPath2(
    private val path: RecordablePath,
    @Contextual private val matrix: Matrix
) : Command {

    override fun execute(path: Path) {
        path.addPath(this.path, matrix)
    }

    override fun toJson(): String {
        return Json.encodeToString(this)
    }

    companion object {
        fun fromJson(json: String): AddPath2 {
            return Json.decodeFromString(json)
        }
    }

}