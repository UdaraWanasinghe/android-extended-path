package com.aureusapps.android.extendedpath.serializers

import android.graphics.Matrix
import kotlinx.serialization.Serializable

internal typealias MatrixAsFloatArray = @Serializable(with = MatrixSerializer::class) Matrix