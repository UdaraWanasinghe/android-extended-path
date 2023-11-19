package com.aureusapps.android.extendedpath

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExtendedPathInstrumentedTest {

    @Test
    fun testExtendedPathSerialization() {
        val path = ExtendedPath()
        path.moveTo(0f, 0f)
        path.lineTo(100f, 100f)
        val json = Json.encodeToString(path)
        assertEquals(
            """{"commands":[{"type":"MoveTo","x":0.0,"y":0.0},{"type":"LineTo","x":100.0,"y":100.0}]}""",
            json
        )
        val path2 = ExtendedPath.fromJson(json)
        path.addPath(path2)
        val json2 = Json.encodeToString(path)
        assertEquals(
            """{"commands":[{"type":"MoveTo","x":0.0,"y":0.0},{"type":"LineTo","x":100.0,"y":100.0},{"type":"AddPath1","path":{"commands":[{"type":"MoveTo","x":0.0,"y":0.0},{"type":"LineTo","x":100.0,"y":100.0}]}}]}""",
            json2
        )
    }

    @Test
    fun testExtendedPathIntersection() {
        val path = ExtendedPath()
        path.moveTo(0f, 0f)
        path.lineTo(9f, 9f)

        var doIntersect = path.doIntersect(3f, 3f)
        assertTrue(doIntersect)

        doIntersect = path.doIntersect(11f, 11f)
        assertTrue(doIntersect)

        doIntersect = path.doIntersect(12f, 12f)
        assertFalse(doIntersect)
    }

}