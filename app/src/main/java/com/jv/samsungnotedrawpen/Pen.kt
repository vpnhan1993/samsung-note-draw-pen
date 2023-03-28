package com.jv.samsungnotedrawpen

import android.graphics.*
import android.util.Log
import androidx.annotation.ColorInt

class Pen {
    private val listPoint by lazy { mutableListOf<PointF>() }
    private var paint: Paint = Paint()

    @ColorInt
    var color: Int = Color.BLACK
    var widthBrush = 1f

    init {
        paint.color = color
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = widthBrush
        paint.isAntiAlias = true
    }

    fun draw(canvas: Canvas?) {
        if (listPoint.size > 1) {
            val path = Path()
            (0 until listPoint.size - 2).forEach { i ->
                val p1 = listPoint[i]
                val p2 = listPoint[i + 1]
                path.moveTo(p1.x, p1.y)
                path.lineTo(p2.x, p2.y)
            }
            canvas?.drawPath(path, paint)
        }
    }

    fun update(point: PointF) {
        listPoint.add(point)
    }
}