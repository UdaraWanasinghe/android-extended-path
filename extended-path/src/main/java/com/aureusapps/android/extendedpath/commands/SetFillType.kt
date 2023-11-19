package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import android.graphics.Path.FillType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("SetFillType")
internal data class SetFillType(
    private val ft: FillType
) : Command {

    override fun execute(path: Path) {
        path.fillType = ft
    }

}