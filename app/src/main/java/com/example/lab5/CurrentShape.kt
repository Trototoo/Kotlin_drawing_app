package com.example.lab5

import com.example.lab5.shapes.*
import java.io.File
import java.io.Serializable

class CurrentShape(private var currentShape: ShapeEnum, path: String) {

    private val limit = 118
    val dynamicArrayOfShape = ArrayList<Shape>(limit)
    var currentIndex: Int = 0
    val tableFile = File(path, "Shapes Table.txt")

    fun getShape(startX: Float, startY: Float, currentX: Float, currentY: Float) : Shape {
        return when (currentShape) {
            ShapeEnum.POINT -> PointShape(startX, startY, currentX, currentY)
            ShapeEnum.LINE -> LineShape(startX, startY, currentX, currentY)
            ShapeEnum.RECTANGLE -> RectangleShape(startX, startY, currentX, currentY)
            ShapeEnum.OVAL -> OvalShape(startX, startY, currentX, currentY)
            ShapeEnum.POINTSLINE -> PointsLineShape(startX, startY, currentX, currentY)
            ShapeEnum.CUBEFRAME -> CubeFrameShape(startX, startY, currentX, currentY)
        }
    }

    fun setPointShape() {
        currentShape = ShapeEnum.POINT
    }

    fun setLineShape() {
        currentShape = ShapeEnum.LINE
    }

    fun setRectangleShape() {
        currentShape = ShapeEnum.RECTANGLE
    }

    fun setOvalShape() {
        currentShape = ShapeEnum.OVAL
    }

    fun setPointsLineShape() {
        currentShape = ShapeEnum.POINTSLINE
    }

    fun setCubeFrameShape() {
        currentShape = ShapeEnum.CUBEFRAME
    }

    fun incrementIndex() {
        currentIndex++
    }

    fun decrementIndex() {
        currentIndex--
    }

    fun resetTableFile() {
        tableFile.writeText("")
        for (e in 0 until currentIndex) {
            val current = dynamicArrayOfShape[e]
            tableFile.appendText(current.getName() + " (${current.startX.toInt()};${current.startY.toInt()}) -> (${current.currentX.toInt()};${current.currentY.toInt()})\n")
        }
    }

}