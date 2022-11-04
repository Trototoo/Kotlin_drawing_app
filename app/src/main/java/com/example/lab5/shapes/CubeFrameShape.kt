package com.example.lab5.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.abs

class CubeFrameShape(startX: Float, startY: Float, currentX: Float, currentY: Float) :
    Shape(startX, startY, currentX, currentY) {

    override var selected: Boolean = false

    override fun drawShape(canvas: Canvas, paint: Paint) {
        selectedStroke(paint)
        val length = if (abs(currentX - startX) < abs(currentY - startY)) abs(currentX -startX) else abs(currentY - startY)

        val smallLength = length / 3
        val bigLength = smallLength * 2

        val left: Float
        val right: Float
        val top: Float
        val bottom: Float

        val secondRectLeft: Float
        val secondRectRight: Float
        val secondRectTop: Float
        val secondRectBottom: Float


        if (startX < currentX) {
            left = startX
            right = left + bigLength
            secondRectLeft = left + smallLength
            secondRectRight = secondRectLeft + bigLength
        } else {
            right = startX
            left = right - bigLength
            secondRectRight = startX - smallLength
            secondRectLeft = secondRectRight - bigLength
        }

        if (startY < currentY) {
            bottom = startY
            top = bottom + bigLength
            secondRectBottom = startY + smallLength
            secondRectTop = secondRectBottom + bigLength
        } else {
            top = startY
            bottom = top - bigLength
            secondRectTop = startY - smallLength
            secondRectBottom = secondRectTop - bigLength
        }

        canvas.drawRect(left, top, right, bottom, paint)
        canvas.drawRect(secondRectLeft, secondRectTop, secondRectRight, secondRectBottom, paint)
        canvas.drawLine(left, bottom, secondRectLeft, secondRectBottom, paint)
        canvas.drawLine(left, top, secondRectLeft, secondRectTop, paint)
        canvas.drawLine(right, bottom, secondRectRight, secondRectBottom, paint)
        canvas.drawLine(right, top, secondRectRight, secondRectTop, paint)

    }

    override fun setPaintStyle(paint: Paint) {
        paint.pathEffect = null
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }

    override fun setFillStyle(paint: Paint) {
        paint.pathEffect = null
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }

    override fun drawSavedShape(canvas: Canvas, paint: Paint) {
        setPaintStyle(paint)
        drawShape(canvas, paint)
    }

    override fun getName(): String {
        return "Куб"
    }
}