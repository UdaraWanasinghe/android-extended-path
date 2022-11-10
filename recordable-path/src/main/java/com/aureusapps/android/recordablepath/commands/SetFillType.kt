package com.aureusapps.android.recordablepath.commands

import android.graphics.Path
import android.graphics.Path.FillType
import kotlinx.serialization.Serializable

@Serializable
internal class SetFillType(
    private val ft: FillType
) : Command {

    override fun execute(path: Path) {
        path.fillType = ft
    }

}