package com.jv.samsungnotedrawpen

import android.graphics.*
import androidx.annotation.ColorInt

class Pen {
    private val listPoint by lazy { mutableListOf<PointF>() }
    private var paint: Paint = Paint()

    @ColorInt
    var color: Int = Color.BLACK
    var widthBrush = 2f

    init {
        paint.color = color
        paint.style = Paint.Style.FILL
        paint.strokeWidth = widthBrush
        paint.isAntiAlias = true
    }

    fun draw(canvas: Canvas?) {
    }

    fun update(point: PointF) {
        listPoint.add(point)
    }
}