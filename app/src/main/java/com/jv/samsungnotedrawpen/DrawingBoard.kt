package com.jv.samsungnotedrawpen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingBoard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val listPen = mutableListOf<Pen>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                startPen(x = event.x, y = event.y)
                true
            }
            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                penDrawing(x = event.x, y = event.y)
                true
            }
            else -> false
        }
    }

    private fun penDrawing(x: Float, y: Float) {
        val pen = listPen.last()
        pen.update(point = PointF(x, y))
        invalidate()
    }

    private fun startPen(x: Float, y: Float) {
        val pen = Pen()
        pen.update(point = PointF(x, y))
        listPen.add(pen)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        listPen.onEach { it.draw(canvas) }
    }
}