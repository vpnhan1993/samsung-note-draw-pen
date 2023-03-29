package com.jv.samsungnotedrawpen

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.BitmapCompat
import androidx.core.graphics.scale

class Eraser(private val context: Context) {
    private var point = PointF()
    private val paint = Paint()

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    fun update(point: PointF) {
        this.point = point
    }

    fun draw(canvas: Canvas?) {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_eraser)
        val w = bitmap.width
        val h = bitmap.height
        val scaleW = 50f / w
        val scaleH = 50f / h
        val matrix = Matrix()
        matrix.postScale(scaleW, scaleH)
        val resized = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false)
        bitmap.recycle()
        canvas?.drawBitmap(
            resized,
            point.x - (resized.width / 2),
            point.y - (resized.height / 2),
            paint
        )
    }
}