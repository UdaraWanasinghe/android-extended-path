package com.aureusapps.android.serializablepath

import android.graphics.Path
import org.junit.Assert
import org.junit.Test

class PathSerializerInstrumentedTest {

    @Test
    fun testSerialize() {
        val path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(0f, 1f)
        path.lineTo(1f, 1f)
        path.lineTo(1f, 0f)
        path.close()
        val serializedPath = PathSerializer.serialize(path, error = 1f)
        Assert.assertEquals(
            """[{"points":[{"x":0.0,"y":0.0},{"x":0.0,"y":1.0},{"x":1.0,"y":1.0},{"x":1.0,"y":0.0},{"x":0.0,"y":0.0}]}]""",
            serializedPath
        )
    }

    @Test
    fun testDeserialize() {
        val serializedPath = """[{"points":[{"x":0.0,"y":0.0},{"x":0.0,"y":1.0},{"x":1.0,"y":1.0},{"x":1.0,"y":0.0},{"x":0.0,"y":0.0}]}]"""
        val path = PathSerializer.deserialize(serializedPath)
        val serializedPath2 = PathSerializer.serialize(path, error = 1f)
        Assert.assertEquals(serializedPath, serializedPath2)
    }

}