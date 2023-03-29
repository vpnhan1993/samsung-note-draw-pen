package com.jv.samsungnotedrawpen

import android.graphics.*
import androidx.annotation.ColorInt
import kotlin.math.roundToInt

class Pen(@ColorInt private val color: Int) {
    private val listPoint by lazy { mutableListOf<PointF>() }
    private var paint: Paint = Paint()

    var widthBrush = 1f
    lateinit var path: Path

    init {
        paint.color = color
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = widthBrush
        paint.isAntiAlias = true
    }

    fun draw(canvas: Canvas?) {
        if (listPoint.size > 1) {
            path = Path()
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

    fun containErase(point: PointF): Boolean {
        val rectF = RectF()
        path.computeBounds(rectF, true)
        return rectF.intersect(point.x, point.y, point.x, point.y)
    }
}