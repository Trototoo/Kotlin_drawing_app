package com.example.lab5.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class RectangleShape(startX: Float, startY: Float, currentX: Float, currentY: Float) :
    Shape(startX, startY, currentX, currentY) {

    override var selected: Boolean = false

    override fun drawShape(canvas: Canvas, paint: Paint) {
        selectedStroke(paint)

        val left = if (startX > currentX) currentX else startX
        val right = if (startX > currentX) startX else currentX
        val top = if (startY > currentY) currentY else startY
        val bottom = if (startY > currentY) startY else currentY

        canvas.drawRect(left, top, right, bottom, paint)
    }

    override fun setFillStyle(paint: Paint) {
        paint.pathEffect = null
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }

    override fun setPaintStyle(paint: Paint) {
        paint.pathEffect = null
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
        }
    }

    override fun drawSavedShape(canvas: Canvas, paint: Paint) {
        setPaintStyle(paint)
        drawShape(canvas, paint)
    }

    override fun getName(): String {
        return "Прямокутник"
    }
}