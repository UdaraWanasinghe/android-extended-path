package com.aureusapps.android.recordablepath

import android.graphics.Matrix
import com.aureusapps.android.recordablepath.serializers.MatrixSerializer
import kotlinx.serialization.Serializable

typealias MatrixAsFloatArray = @Serializable(with = MatrixSerializer::class) Matrix