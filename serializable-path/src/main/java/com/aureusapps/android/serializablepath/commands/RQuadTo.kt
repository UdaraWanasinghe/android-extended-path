package com.aureusapps.android.serializablepath.commands

import android.graphics.Path

internal class RQuadTo(
    private val dx1: Float,
    private val dy1: Float,
    private val dx2: Float,
    private val dy2: Float,
    private val moveToOrigin: Boolean
) : Command {

    override fun execute(path: Path) {
        path.rQuadTo(dx1, dy1, dx2, dy2)
    }

    override fun toPathData(): String {
        return if (moveToOrigin) {
            "M0,0" +
                    "q$dx1,$dy1,$dx2,$dy2"
        } else {
            "q$dx1,$dy1,$dx2,$dy2"
        }
    }

    override val isClosed: Boolean = false

}