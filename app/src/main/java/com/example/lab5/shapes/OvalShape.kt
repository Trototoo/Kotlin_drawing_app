package com.example.lab5.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class OvalShape(startX: Float, startY: Float, currentX: Float, currentY: Float) :
    Shape(startX, startY, currentX, currentY) {

    override var selected: Boolean = false

    override fun drawShape(canvas: Canvas, paint: Paint) {
        selectedStroke(paint)
        val otherX = 2 * startX - currentX
        val otherY = 2 * startY - currentY

        val left = if (otherX > currentX) currentX else otherX
        val right = if (otherX > currentX) otherX else currentX
        val top = if (otherY > currentY) otherY else currentY
        val bottom = if (otherY > currentY) currentY else otherY

        canvas.drawOval(left, top, right, bottom, paint)
    }

    override fun setPaintStyle(paint: Paint) {
        paint.pathEffect = null
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }

    override fun setFillStyle(paint: Paint) {
        paint.pathEffect = null
        paint.color = Color.GRAY
        paint.style = Paint.Style.FILL
    }

    override fun drawSavedShape(canvas: Canvas, paint: Paint) {
        setFillStyle(paint)
        drawShape(canvas, paint)
        setPaintStyle(paint)
        drawShape(canvas, paint)
    }

    override fun clone(): Shape {
        return OvalShape(startX, startY, currentX, currentY)
    }

    override fun getName(): String {
        return "Овал"
    }
}