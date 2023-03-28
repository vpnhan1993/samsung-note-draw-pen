package com.jv.samsungnotedrawpen

import android.graphics.*

class Pen {
    private val listPoint by lazy { mutableListOf<PointF>() }
    private var paint: Paint = Paint()

    init {
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
    }

    fun draw(canvas: Canvas?) {
        val arrayPoints = mutableListOf<Float>()
        listPoint.forEach { point ->
            arrayPoints.add(point.x)
            arrayPoints.add(point.y)
        }
        canvas?.drawPoints(arrayPoints.toFloatArray(), paint)
    }

    fun update(point: PointF) {
        listPoint.add(point)
    }
}