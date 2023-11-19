package com.aureusapps.android.extendedpath.commands

import android.graphics.Path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("AddRoundRect1")
internal data class AddRoundRect1(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val radii: FloatArray,
    private val dir: Path.Direction
) : Command {

    override fun execute(path: Path) {
        path.addRoundRect(left, top, right, bottom, radii, dir)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AddRoundRect1

        if (left != other.left) return false
        if (top != other.top) return false
        if (right != other.right) return false
        if (bottom != other.bottom) return false
        if (!radii.contentEquals(other.radii)) return false
        return dir == other.dir
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + top.hashCode()
        result = 31 * result + right.hashCode()
        result = 31 * result + bottom.hashCode()
        result = 31 * result + radii.contentHashCode()
        result = 31 * result + dir.hashCode()
        return result
    }

}