package com.aureusapps.android.recordablepath

import android.graphics.Matrix
import kotlinx.serialization.Serializable

internal typealias MatrixAsFloatArray = @Serializable(with = MatrixSerializer::class) Matrix