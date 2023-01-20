package com.example.lab5.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint

abstract class Shape(
    var startX: Float,
    var startY: Float,
    var currentX: Float,
    var currentY: Float
) : Cloneable {
    abstract var selected: Boolean
    abstract fun drawShape(canvas: Canvas, paint: Paint)
    abstract fun setPaintStyle(paint: Paint)
    abstract fun setFillStyle(paint: Paint)
    abstract fun drawSavedShape(canvas: Canvas, paint: Paint)
    private val dashLength = 11f
    private val gapLength = 40f
    private val dashOffset = 1f
    public override fun clone(): Shape {
        return super.clone() as Shape
    }

    fun selectedStroke(paint: Paint) {
        paint.strokeWidth = if (selected) 20f else 10f
    }
    abstract fun getName(): String

    fun setTrailStrokeStyle(paint: Paint) {
        paint.pathEffect = DashPathEffect(floatArrayOf(dashLength, gapLength), dashOffset)
        paint.color = Color.BLACK
    }
}
