package com.aureusapps.android.recordablepath.commands

import kotlinx.serialization.Serializable

@Serializable
internal object ToggleInverseFillType : Command {

    override fun execute(path: android.graphics.Path) {
        path.toggleInverseFillType()
    }

}