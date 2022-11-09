package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import org.json.JSONObject

internal class Close: Command {

    override fun execute(path: Path) {
        path.close()
    }

    override fun toJson(): JSONObject {
        return JSONObject(
            """{"Close":{}}"""
        )
    }

}