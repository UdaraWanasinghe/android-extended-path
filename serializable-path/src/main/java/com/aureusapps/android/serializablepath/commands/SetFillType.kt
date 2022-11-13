package com.aureusapps.android.serializablepath.commands

import android.graphics.Path
import android.graphics.Path.FillType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("SetFillType")
internal class SetFillType(
    private val ft: FillType
) : PathCommand {

    override fun execute(path: Path) {
        path.fillType = ft
    }

}