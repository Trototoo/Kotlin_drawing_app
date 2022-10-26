package com.example.lab5.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab5.ShapeEnum
import kotlin.math.*

class PointsLineShape(startX: Float, startY: Float, currentX: Float, currentY: Float) :
    Shape(startX, startY, currentX, currentY) {

    override var selected: Boolean = false

    override fun drawShape(canvas: Canvas, paint: Paint) {
        val radius = 70f

        val left = if (startX > currentX) currentX else startX
        val right = if (startX > currentX) startX else currentX
        val top = if (startY > currentY) startY else currentY
        val bottom = if (startY > currentY) currentY else startY

        val length = right - left
        val height = top - bottom

        val angle = atan(height / length)
        val shiftX = radius * cos(angle)
        val shiftY = radius * sin(angle)

        val firstX = if (startX == left) (startX + shiftX) else (startX - shiftX)
        val firstY = if (startY == bottom) (startY + shiftY) else (startY - shiftY)
        val secondX = if (currentX == left) (currentX + shiftX) else (currentX - shiftX)
        val secondY = if (currentY == bottom) (currentY + shiftY) else (currentY - shiftY)

        val distance = sqrt((currentX - startX).pow(2) + (currentY - startY).pow(2))

        if (distance > radius * 2) {
            canvas.drawLine(firstX, firstY, secondX, secondY, paint)
        }
        canvas.drawCircle(startX, startY, radius, paint)
        canvas.drawCircle(currentX, currentY, radius, paint)
    }

    override fun setPaintStyle(paint: Paint) {
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }

    override fun setFillStyle(paint: Paint) {
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
    }

    override fun drawSavedShape(canvas: Canvas, paint: Paint) {
        selectedStroke(paint)
        setPaintStyle(paint)
        drawShape(canvas, paint)
    }

    override fun getName(): String {
        return "Гантеля"
    }

    override fun getType(): ShapeEnum {
        return ShapeEnum.POINTSLINE
    }
}