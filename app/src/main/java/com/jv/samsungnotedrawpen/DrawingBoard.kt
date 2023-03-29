package com.jv.samsungnotedrawpen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingBoard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var color: Int = Color.BLACK
    private var isErasing = false
    private var eraser: Eraser? = null
    private val listPen = mutableListOf<Pen>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (isErasing) erasing(x = event.x, y = event.y)
                else startPen(x = event.x, y = event.y)
                true
            }
            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                if (isErasing) erasing(x = event.x, y = event.y)
                else penDrawing(x = event.x, y = event.y)
                true
            }
            else -> false
        }
    }

    private fun erasing(x: Float, y: Float) {
        eraser?.update(point = PointF(x, y))
        val penRemoves = listPen.filter { pen -> pen.containErase(PointF(x, y)) }
        penRemoves.forEach { listPen.remove(it) }
        invalidate()
    }

    private fun penDrawing(x: Float, y: Float) {
        val pen = listPen.last()
        pen.update(point = PointF(x, y))
        invalidate()
    }

    private fun startPen(x: Float, y: Float) {
        val pen = Pen(color = color)
        pen.update(point = PointF(x, y))
        listPen.add(pen)
        invalidate()
    }

    fun setEraser() {
        isErasing = !isErasing
        eraser = if (isErasing) Eraser(context) else null
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isErasing) eraser?.draw(canvas)
        listPen.onEach { it.draw(canvas) }
    }
}