package com.aureusapps.android.serializablepath.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ToggleInverseFillType")
internal object ToggleInverseFillType : Command {

    override fun execute(path: android.graphics.Path) {
        path.toggleInverseFillType()
    }

}