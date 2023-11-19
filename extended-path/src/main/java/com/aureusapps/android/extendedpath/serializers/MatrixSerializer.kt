package com.aureusapps.android.extendedpath.serializers

import android.graphics.Matrix
import androidx.core.graphics.values
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.FloatArraySerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal class MatrixSerializer : KSerializer<Matrix> {

    private val delegate = FloatArraySerializer()

    override val descriptor = delegate.descriptor

    override fun deserialize(decoder: Decoder): Matrix {
        val array = delegate.deserialize(decoder)
        val matrix = Matrix()
        matrix.setValues(array)
        return matrix
    }

    override fun serialize(encoder: Encoder, value: Matrix) {
        val array = value.values()
        delegate.serialize(encoder, array)
    }

}