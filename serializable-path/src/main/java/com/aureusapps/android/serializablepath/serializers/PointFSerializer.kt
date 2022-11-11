package com.aureusapps.android.serializablepath.serializers

import android.graphics.PointF
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalSerializationApi::class)

internal class PointFSerializer : KSerializer<PointF> {

    private val delegateSerializer = MapSerializer(String.serializer(), Float.serializer())

    override val descriptor = SerialDescriptor("PointF", delegateSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: PointF) {
        encoder.encodeSerializableValue(delegateSerializer, mapOf("x" to value.x, "y" to value.y))
    }

    override fun deserialize(decoder: Decoder): PointF {
        val m = decoder.decodeSerializableValue(delegateSerializer)
        return PointF(m["x"]!!, m["y"]!!)
    }

}