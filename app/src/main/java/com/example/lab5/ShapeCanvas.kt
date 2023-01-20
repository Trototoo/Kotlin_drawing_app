package com.example.lab5

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.example.lab5.shapes.*

private const val STROKE_WIDTH = 10f

class ShapeCanvas(
    context: Context,
    private var utilities: Utilities
) : View(context) {

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
        val currentShape = utilities.currentShape.clone()
        currentShape.startX = startX
        currentShape.startY = startY
        currentShape.currentX = currentX
        currentShape.currentY = currentY
        return currentShape
    }

    private var currentX = 0f
    private var currentY = 0f
    private var dynamicShapesArray: ArrayList<Shape> = utilities.dynamicArrayOfShape

    private var currentActionState = CurrentTouchState.UP

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
        if (currentActionState == CurrentTouchState.DOWN || currentActionState == CurrentTouchState.MOVE) {
            dynamicShapesArray[utilities.currentIndex].setTrailStrokeStyle(paint)
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
        currentActionState = CurrentTouchState.UP
        val current = dynamicShapesArray[utilities.currentIndex]
        current.currentX = currentX
        current.currentY = currentY
        invalidate()
        val pointShapeClassName = PointShape(0f, 0f, 0f, 0f).javaClass.name
        val className = current.javaClass.name
        val name = current.getName()
        val start = "(${current.startX.toInt()};${current.startY.toInt()})"
        val end = if (className != pointShapeClassName) " -> (${current.currentX.toInt()};${current.currentY.toInt()})\n" else "\n"
        val appendString = "$name $start$end"
        utilities.tableFile.appendText(appendString)

        utilities.incrementIndex()
    }

    private fun touchMove() {
        currentActionState = CurrentTouchState.MOVE
        val current = dynamicShapesArray[utilities.currentIndex]
        current.currentX = currentX
        current.currentY = currentY
        invalidate()
    }

    private fun touchStart() {
        currentActionState = CurrentTouchState.DOWN
        dynamicShapesArray.add(utilities.currentIndex, createShape(currentX, currentY, currentX, currentY))
    }
    fun resetCanvas() {
        utilities.currentIndex = 0
        dynamicShapesArray.clear()
        utilities.resetTableFile()
        invalidate()
    }

    enum class CurrentTouchState {
        UP, DOWN, MOVE
    }
}
