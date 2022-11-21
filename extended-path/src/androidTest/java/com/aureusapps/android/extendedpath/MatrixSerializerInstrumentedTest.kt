package com.aureusapps.android.extendedpath

import android.graphics.Matrix
import androidx.core.graphics.values
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aureusapps.android.extendedpath.serializers.MatrixSerializer
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatrixSerializerInstrumentedTest {

    @Test
    fun testMatrixSerializer() {
        val matrix = Matrix()
        matrix.setValues(floatArrayOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f))
        val json = Json.encodeToString(MatrixSerializer(), matrix)
        val matrix2 = Json.decodeFromString(MatrixSerializer(), json)
        Assert.assertArrayEquals(matrix.values(), matrix2.values(), 0f)
    }

}