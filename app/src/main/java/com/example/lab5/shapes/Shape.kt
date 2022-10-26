package com.example.lab5.shapes

import android.graphics.Canvas
import android.graphics.Paint
import com.example.lab5.ShapeEnum

abstract class Shape (
    var startX: Float,
    var startY: Float,
    var currentX: Float,
    var currentY: Float,
) {
    abstract var selected: Boolean
    abstract fun drawShape(canvas: Canvas, paint: Paint)
    abstract fun setPaintStyle(paint: Paint)
    abstract fun setFillStyle(paint: Paint)
    abstract fun drawSavedShape(canvas: Canvas, paint: Paint)
    fun selectedStroke(paint: Paint) {
        paint.strokeWidth = if(selected) 20f else 10f
    }
    abstract fun getName(): String
    abstract fun getType(): ShapeEnum

}