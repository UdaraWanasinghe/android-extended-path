package com.aureusapps.android.serializablepath.example

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val path = Path()
        path.moveTo(1000f, 1000f)
        path.addArc(100f, 100f, 200f, 200f, 45f, 90f)
        val view = object : View(this) {
            val paint = Paint().apply {
                color = Color.RED
                style = Paint.Style.STROKE
                strokeWidth = 10f
            }

            override fun onDraw(canvas: Canvas) {
                canvas.drawPath(path, paint)
            }
        }.apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }
        setContentView(view)
    }
}