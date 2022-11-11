package com.aureusapps.android.serializablepath.serializers

import android.graphics.Matrix
import android.graphics.PointF
import kotlinx.serialization.Serializable

internal typealias MatrixAsFloatArray = @Serializable(with = MatrixSerializer::class) Matrix

internal typealias PointFAsMap = @Serializable(with = PointFSerializer::class) PointF