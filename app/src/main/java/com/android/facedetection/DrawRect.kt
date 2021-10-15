package com.android.facedetection

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import com.google.mlkit.vision.face.Face

class DrawRect (context: Context, var faces:List<Face>) : View(context)
{

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paint=Paint()
        for (item in faces)
        {
            paint.color=Color.RED
            paint.strokeWidth=8f
            paint.style=Paint.Style.STROKE
            val box = item.boundingBox
            canvas.drawRect(box, paint)
        }

    }
}