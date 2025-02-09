package com.example.lab5.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.*

class PointsLineShape(startX: Float, startY: Float, currentX: Float, currentY: Float) :
    Shape(startX, startY, currentX, currentY) {

    override var selected: Boolean = false
    private val radius = 70f

    override fun drawShape(canvas: Canvas, paint: Paint) {
        selectedStroke(paint)

        val distance = sqrt((currentX - startX).pow(2) + (currentY - startY).pow(2))

        val lineCords = getLineCords()

        val firstX = lineCords["firstX"]
        val firstY = lineCords["firstY"]
        val secondX = lineCords["secondX"]
        val secondY = lineCords["secondY"]

        if (distance > radius * 2) {
            canvas.drawLine(firstX!!, firstY!!, secondX!!, secondY!!, paint)
        }
        canvas.drawCircle(startX, startY, radius, paint)
        canvas.drawCircle(currentX, currentY, radius, paint)
    }

    private fun getLineCords(): Map<String, Float> {
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

        return mapOf("firstX" to firstX, "firstY" to firstY, "secondX" to secondX, "secondY" to secondY)
    }

    override fun setPaintStyle(paint: Paint) {
        paint.pathEffect = null
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }

    override fun setFillStyle(paint: Paint) {
        paint.pathEffect = null
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
    }

    override fun drawSavedShape(canvas: Canvas, paint: Paint) {
        setPaintStyle(paint)
        drawShape(canvas, paint)
    }

    override fun getName(): String {
        return "Гантеля"
    }
}
