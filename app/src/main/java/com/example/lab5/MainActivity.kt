package com.example.lab5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.lab5.shapes.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class MainActivity : AppCompatActivity() {

    private lateinit var shapeCanvas: ShapeCanvas

    private var menu: Menu? = null

    companion object {
        val dynamicArrayOfShape = ArrayList<Shape>(118)
        var currentIndex: Int = 0
        val tableFile = File("/data/user/0/com.example.lab5/files", "Shapes Table.txt")
        var currentShape: Shape = PointShape(0f, 0f, 0f, 0f)
        fun resetTableFile() {
            tableFile.writeText("")
            for (e in 0 until currentIndex) {
                val current = dynamicArrayOfShape[e]
                if (currentShape::class == PointShape(0f, 0f, 0f, 0f)::class) {
                    tableFile.appendText(current.getName() + " (${current.startX.toInt()};${current.startY.toInt()})\n")
                } else {
                    tableFile.appendText(current.getName() + " (${current.startX.toInt()};${current.startY.toInt()}) -> (${current.currentX.toInt()};${current.currentY.toInt()})\n")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
        if (!File("/data/user/0/com.example.lab5/files").isDirectory) {
            Files.createDirectory(Paths.get("/data/user/0/com.example.lab5/files"))
        }
        tableFile.writeText("")

        shapeCanvas = ShapeCanvas(this)
        setContentView(shapeCanvas)

        setPointShape()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        this.menu = menu
        return true
    }

    private val buttonsAction = mapOf(
        R.id.point to ::setPointShape,
        R.id.line to ::setLineShape,
        R.id.rectangle to ::setRectangleShape,
        R.id.oval to ::setOvalShape,
        R.id.pointsLine to ::setPointsLineShape,
        R.id.cubeFrame to ::setCubeFrameShape,
        R.id.pointMenu to ::setPointShape,
        R.id.lineMenu to ::setLineShape,
        R.id.rectangleMenu to ::setRectangleShape,
        R.id.ovalMenu to ::setOvalShape,
        R.id.pointsLineMenu to ::setPointsLineShape,
        R.id.cubeFrameMenu to ::setCubeFrameShape,
        R.id.resetBtn to { shapeCanvas.resetCanvas() },
        R.id.openTableBtn to ::openTableActivity
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        buttonsAction[item.itemId]?.invoke()
        return true
    }

    private fun openTableActivity() {
        val intent = Intent(this, ShapesTableActivity::class.java)
        startActivity(intent)
    }

    private fun setPointShape() {
        currentShape = PointShape(0f, 0f, 0f, 0f)
        setOffIcons()
        setOffMenu()
        menu?.findItem(R.id.point)?.setIcon(R.drawable.ic_point_on)
        menu?.findItem(R.id.pointMenu)?.isChecked = true
        supportActionBar?.title = "Point"
    }

    private fun setLineShape() {
        currentShape = LineShape(0f, 0f, 0f, 0f)
        setOffIcons()
        setOffMenu()
        menu?.findItem(R.id.line)?.setIcon(R.drawable.ic_line_on)
        menu?.findItem(R.id.lineMenu)?.isChecked = true
        supportActionBar?.title = "Line"
    }

    private fun setRectangleShape() {
        currentShape = RectangleShape(0f, 0f, 0f, 0f)
        setOffIcons()
        setOffMenu()
        menu?.findItem(R.id.rectangle)?.setIcon(R.drawable.ic_rectangle_on)
        menu?.findItem(R.id.rectangleMenu)?.isChecked = true
        supportActionBar?.title = "Rect"
    }

    private fun setOvalShape() {
        currentShape = OvalShape(0f, 0f, 0f, 0f)
        setOffIcons()
        setOffMenu()
        menu?.findItem(R.id.oval)?.setIcon(R.drawable.ic_oval_on)
        menu?.findItem(R.id.ovalMenu)?.isChecked = true
        supportActionBar?.title = "Oval"
    }

    private fun setPointsLineShape() {
        currentShape = PointsLineShape(0f, 0f, 0f, 0f)
        setOffIcons()
        setOffMenu()
        menu?.findItem(R.id.pointsLine)?.setIcon(R.drawable.ic_points_line_on)
        menu?.findItem(R.id.pointsLineMenu)?.isChecked = true
        supportActionBar?.title = "PntsL"
    }

    private fun setCubeFrameShape() {
        currentShape = CubeFrameShape(0f, 0f, 0f, 0f)
        setOffIcons()
        setOffMenu()
        menu?.findItem(R.id.cubeFrame)?.setIcon(R.drawable.ic_cube_frame_on)
        menu?.findItem(R.id.cubeFrameMenu)?.isChecked = true
        supportActionBar?.title = "Frame"
    }

    private fun setOffIcons() {
        menu?.findItem(R.id.point)?.setIcon(R.drawable.ic_point_off)
        menu?.findItem(R.id.line)?.setIcon(R.drawable.ic_line_off)
        menu?.findItem(R.id.rectangle)?.setIcon(R.drawable.ic_rectangle_off)
        menu?.findItem(R.id.oval)?.setIcon(R.drawable.ic_oval_off)
        menu?.findItem(R.id.pointsLine)?.setIcon(R.drawable.ic_points_line_off)
        menu?.findItem(R.id.cubeFrame)?.setIcon(R.drawable.ic_cube_frame_off)
    }

    private fun setOffMenu() {
        menu?.findItem(R.id.pointMenu)?.isChecked = false
        menu?.findItem(R.id.lineMenu)?.isChecked = false
        menu?.findItem(R.id.rectangleMenu)?.isChecked = false
        menu?.findItem(R.id.ovalMenu)?.isChecked = false
        menu?.findItem(R.id.pointsLineMenu)?.isChecked = false
        menu?.findItem(R.id.cubeFrameMenu)?.isChecked = false
    }
}
