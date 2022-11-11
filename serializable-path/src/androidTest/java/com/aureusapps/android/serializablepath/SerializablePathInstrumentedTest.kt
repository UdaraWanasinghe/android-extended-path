package com.aureusapps.android.serializablepath

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aureusapps.android.serializablepath.commands.AddPath1
import com.aureusapps.android.serializablepath.commands.LineTo
import com.aureusapps.android.serializablepath.commands.MoveTo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SerializablePathInstrumentedTest {

    @Test
    fun testSerializablePath() {
        val path = SerializablePath()
        path.moveTo(0f, 0f)
        path.lineTo(100f, 100f)
        val json = Json.encodeToString(path)
        Assert.assertEquals(
            """{"commands":[{"type":"${MoveTo::class.qualifiedName}","x":0.0,"y":0.0},{"type":"${LineTo::class.qualifiedName}","x":100.0,"y":100.0}]}""".trimMargin(),
            json
        )
        val path2 = SerializablePath.fromJson(json)
        path.addPath(path2)
        val json2 = Json.encodeToString(path)
        Assert.assertEquals(
            """{"commands":[{"type":"${MoveTo::class.qualifiedName}","x":0.0,"y":0.0},{"type":"${LineTo::class.qualifiedName}","x":100.0,"y":100.0},{"type":"${AddPath1::class.qualifiedName}","path":{"commands":[{"type":"${MoveTo::class.qualifiedName}","x":0.0,"y":0.0},{"type":"${LineTo::class.qualifiedName}","x":100.0,"y":100.0}]}}]}""",
            json2
        )
    }

}