package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import android.graphics.Path.FillType
import kotlinx.serialization.Serializable

@Serializable
internal class SetFillType(
    private val ft: FillType
) : PathCommand {

    override fun execute(path: Path) {
        path.fillType = ft
    }

}