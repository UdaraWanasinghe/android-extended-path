package com.aureusapps.android.recordablepath

import android.graphics.Matrix
import androidx.core.graphics.values
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.FloatArraySerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalSerializationApi::class)

internal class MatrixSerializer : KSerializer<Matrix> {
    private val delegateSerializer = FloatArraySerializer()

    override fun deserialize(decoder: Decoder): Matrix {
        val array = decoder.decodeSerializableValue(delegateSerializer)
        return Matrix().apply { setValues(array) }
    }

    override val descriptor = SerialDescriptor("Matrix", delegateSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: Matrix) {
        encoder.encodeSerializableValue(delegateSerializer, value.values())
    }
}