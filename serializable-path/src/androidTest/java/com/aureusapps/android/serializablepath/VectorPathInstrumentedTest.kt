package com.aureusapps.android.serializablepath

import android.graphics.Path
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aureusapps.android.serializablepath.cmds.AddArc
import com.aureusapps.android.serializablepath.cmds.AddRect
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VectorPathInstrumentedTest {

    @Test
    fun testAddRect() {
        val command = AddRect(0f, 0f, 100f, 100f, Path.Direction.CW)
        val pathData = command.toPathData()
        Assert.assertEquals("M0.0,0.0L100.0,0.0L100.0,100.0L0.0,100.0L0.0,0.0Z", pathData)
    }

    @Test
    fun testAddArc() {
        val command = AddArc(100f, 100f, 300f, 200f, 25f, 50f)
        val pathData = command.toPathData()
        val sx = pathData.substring(1, pathData.indexOf(',')).toFloat()
        val sy = pathData.substring(pathData.indexOf(',') + 1, pathData.indexOf('A')).toFloat()
        Assert.assertEquals(273.13f, sx, 0.1f)
        Assert.assertEquals(184.10f, sy, 0.1f)
        val ex = pathData.substring(pathData.lastIndexOf("0,") + 2, pathData.lastIndexOf(',')).toFloat()
        val ey = pathData.substring(pathData.lastIndexOf(',') + 1, pathData.lastIndexOf('Z')).toFloat()
        Assert.assertEquals(213.27f, ex, 0.1f)
        Assert.assertEquals(199.55f, ey, 0.1f)

    }

}