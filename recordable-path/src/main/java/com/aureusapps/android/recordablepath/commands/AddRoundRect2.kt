package com.aureusapps.android.recordablepath.commands

import android.graphics.Path

class AddRoundRect2(
    val left: Float,
    val top: Float,
    val right: Float,
    val bottom: Float,
    val rx: Float,
    val ry: Float,
    val dir: Path.Direction
) {
}