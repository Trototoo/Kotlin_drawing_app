package com.example.lab5.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.lab5.ShapeEnum

class LineShape(startX: Float, startY: Float, currentX: Float, currentY: Float) :
    Shape(startX, startY, currentX, currentY) {

    override var selected: Boolean = false

    override fun drawShape(canvas: Canvas, paint: Paint) {
        canvas.drawLine(startX, startY, currentX, currentY, paint)
    }

    override fun setPaintStyle(paint: Paint) {
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }

    override fun setFillStyle(paint: Paint) {
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }

    override fun drawSavedShape(canvas: Canvas, paint: Paint) {
        selectedStroke(paint)
        setPaintStyle(paint)
        drawShape(canvas, paint)
    }

    override fun getName(): String {
        return "Лінія"
    }

    override fun getType(): ShapeEnum {
        return ShapeEnum.LINE
    }

}