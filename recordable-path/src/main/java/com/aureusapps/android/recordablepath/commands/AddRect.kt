package com.aureusapps.android.recordablepath.commands

import android.graphics.Path

class AddRect(
    val left: Float,
    val top: Float,
    val right: Float,
    val bottom: Float,
    val dir: Path.Direction
) {
}