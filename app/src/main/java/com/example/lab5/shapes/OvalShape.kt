package com.example.lab5.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class OvalShape(startX: Float, startY: Float, currentX: Float, currentY: Float) :
    Shape(startX, startY, currentX, currentY) {

    override var selected: Boolean = false

    override fun drawShape(canvas: Canvas, paint: Paint) {
        selectedStroke(paint)
        // We get cords of central dot and one of corners and calculate other corner cords with easy mathematical formula
        val otherCornerX = 2 * startX - currentX
        val otherCornerY = 2 * startY - currentY

        val left = if (otherCornerX > currentX) currentX else otherCornerX
        val right = if (otherCornerX > currentX) otherCornerX else currentX
        val top = if (otherCornerY > currentY) otherCornerY else currentY
        val bottom = if (otherCornerY > currentY) currentY else otherCornerY

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

    override fun getName(): String {
        return "Овал"
    }
}
