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
    private var lastTime = 0L

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val diff = System.currentTimeMillis() - lastTime
        if (diff <= 100) {
            return super.onTouchEvent(event)
        }
        lastTime = System.currentTimeMillis()
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                startLine(x = event.x, y = event.y)
                true
            }
            MotionEvent.ACTION_MOVE -> {
                drawingLine(x = event.x, y = event.y)
                true
            }
            MotionEvent.ACTION_UP -> {
                endLine(x = event.x, y = event.y)
                true
            }
            else -> false
        }
    }

    private fun endLine(x: Float, y: Float) {
        val pen = listPen.last()
        pen.update(point = PointF(x, y))
        invalidate()
    }

    private fun drawingLine(x: Float, y: Float) {
        val pen = listPen.last()
        pen.update(point = PointF(x, y))
        invalidate()
    }

    private fun startLine(x: Float, y: Float) {
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