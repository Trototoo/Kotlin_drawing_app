package com.example.lab5

import com.example.lab5.shapes.*
import java.io.File

class Utilities(var currentShape: Shape, path: String) {

    private val limit = 118
    val dynamicArrayOfShape = ArrayList<Shape>(limit)
    var currentIndex: Int = 0
    val tableFile = File(path, "Shapes Table.txt")

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