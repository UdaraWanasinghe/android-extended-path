package com.aureusapps.android.serializablepath.commands

import kotlinx.serialization.Serializable

@Serializable
internal object ToggleInverseFillType : PathCommand {

    override fun execute(path: android.graphics.Path) {
        path.toggleInverseFillType()
    }

}