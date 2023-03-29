package com.jv.samsungnotedrawpen

import android.content.Context
import android.graphics.*

class Eraser(private val context: Context) {
    private var point = PointF(100f, 100f)
    private val paint = Paint()

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    fun update(point: PointF) {
        this.point = point
    }

    fun draw(canvas: Canvas?) {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_eraser)
        val w = 50f //actual width
        val h = 50f // actual height
        val scaleW = w / bitmap.width
        val scaleH = h / bitmap.height
        val matrix = Matrix()
        matrix.postScale(scaleW, scaleH)
        val resized = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
        bitmap.recycle()
        canvas?.drawBitmap(
            resized,
            point.x - w / 2,
            point.y - h / 2,
            paint
        )
    }
}