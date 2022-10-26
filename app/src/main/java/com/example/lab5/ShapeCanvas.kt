package com.example.lab5

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.example.lab5.shapes.*

private const val STROKE_WIDTH = 10f

class ShapeCanvas(
    context: Context,
    private var utilities: Utilities,
) : View(context) {
    private val trailColor = Color.BLUE

    private val paint = Paint().apply {
        isAntiAlias
        isDither
        color = Color.BLACK
        style = Paint.Style.STROKE

        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = STROKE_WIDTH
    }

    private fun createShape(startX: Float, startY: Float, currentX: Float, currentY: Float): Shape {
        return utilities.getShape(startX, startY, currentX, currentY)
    }

    private fun setTrailStrokeColor() {
        this.paint.style = Paint.Style.STROKE
        this.paint.color = trailColor
    }

    private var currentX = 0f
    private var currentY = 0f
    private var dynamicShapesArray: ArrayList<Shape> = utilities.dynamicArrayOfShape

    private var currentActionState = "Nothing"

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        currentX = event!!.x
        currentY = event.y
        onTouchEventShape(event)
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawShapes(canvas)
    }

    private fun drawShapes(canvas: Canvas?) {
        for (e in 0 until utilities.currentIndex) {
            dynamicShapesArray[e].drawSavedShape(canvas!!, paint)
        }
        if (currentActionState == "ACTION_DOWN" || currentActionState == "ACTION_MOVE") {
            setTrailStrokeColor()
            dynamicShapesArray[utilities.currentIndex].drawShape(canvas!!, paint)
        }
    }

    private fun onTouchEventShape(event: MotionEvent) {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
    }

    private fun touchUp() {
        currentActionState = "ACTION_UP"
        val current = dynamicShapesArray[utilities.currentIndex]
        current.currentX = currentX
        current.currentY = currentY
        invalidate()
        if (current.getType() != ShapeEnum.POINT) {
            utilities.tableFile.appendText(current.getName() + " (${current.startX.toInt()};${current.startY.toInt()}) -> (${current.currentX.toInt()};${current.currentY.toInt()})\n")
        } else {
            utilities.tableFile.appendText(current.getName() + " (${current.startX.toInt()};${current.startY.toInt()})\n")
        }

        utilities.incrementIndex()
    }

    private fun touchMove() {
        currentActionState = "ACTION_MOVE"
        val current = dynamicShapesArray[utilities.currentIndex]
        current.currentX = currentX
        current.currentY = currentY
        invalidate()
    }

    private fun touchStart() {
        currentActionState = "ACTION_DOWN"
        dynamicShapesArray.add(utilities.currentIndex, createShape(currentX, currentY, currentX, currentY))
    }
    fun resetCanvas() {
        utilities.currentIndex = 0
        dynamicShapesArray.clear()
        utilities.resetTableFile()
        invalidate()
    }
}