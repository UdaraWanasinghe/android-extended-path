package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import org.json.JSONObject

class MoveTo(
    private val x: Float,
    private val y: Float
): Command {

    override fun execute(path: Path) {
        path.moveTo(x, y)
    }

    override fun toJson(): JSONObject {
        return JSONObject(
            """{"MoveTo":{"x":$x,"y":$y}}"""
        )
    }

}