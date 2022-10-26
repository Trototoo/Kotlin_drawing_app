package com.example.lab5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate



class MainActivity : AppCompatActivity() {

    private lateinit var shapeCanvas: ShapeCanvas

    private var menu: Menu? = null

    companion object {
        val currentShape: CurrentShape = CurrentShape(ShapeEnum.POINT, "/data/user/0/com.example.lab5/files")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
        currentShape.tableFile.writeText("")

        shapeCanvas = ShapeCanvas(this, currentShape)
        setContentView(shapeCanvas)


        setPointShape()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.point -> setPointShape()
            R.id.line -> setLineShape()
            R.id.rectangle -> setRectangleShape()
            R.id.oval -> setOvalShape()
            R.id.pointsLine -> setPointsLineShape()
            R.id.cubeFrame -> setCubeFrameShape()
            R.id.pointMenu -> setPointShape()
            R.id.lineMenu -> setLineShape()
            R.id.rectangleMenu -> setRectangleShape()
            R.id.ovalMenu -> setOvalShape()
            R.id.pointsLineMenu -> setPointsLineShape()
            R.id.cubeFrameMenu -> setCubeFrameShape()
            R.id.resetBtn -> shapeCanvas.resetCanvas()
            R.id.openTableBtn -> openTableActivity()
        }
        return true
    }

    private fun openTableActivity() {
        val intent = Intent(this, ShapesTableActivity::class.java)
        startActivity(intent)
    }

    private fun setPointShape() {
        currentShape.setPointShape()
        setOffIcons()
        setOffMenu()
        menu?.findItem(R.id.point)?.setIcon(R.drawable.ic_point_on)
        menu?.findItem(R.id.pointMenu)?.isChecked = true
        supportActionBar?.title = "Point"
    }

    private fun setLineShape() {
        currentShape.setLineShape()
        setOffIcons()
        setOffMenu()
        menu?.findItem(R.id.line)?.setIcon(R.drawable.ic_line_on)
        menu?.findItem(R.id.lineMenu)?.isChecked = true
        supportActionBar?.title = "Line"
    }

    private fun setRectangleShape() {
        currentShape.setRectangleShape()
        setOffIcons()
        setOffMenu()
        menu?.findItem(R.id.rectangle)?.setIcon(R.drawable.ic_rectangle_on)
        menu?.findItem(R.id.rectangleMenu)?.isChecked = true
        supportActionBar?.title = "Rect"
    }

    private fun setOvalShape() {
        currentShape.setOvalShape()
        setOffIcons()
        setOffMenu()
        menu?.findItem(R.id.oval)?.setIcon(R.drawable.ic_oval_on)
        menu?.findItem(R.id.ovalMenu)?.isChecked = true
        supportActionBar?.title = "Oval"
    }

    private fun setPointsLineShape() {
        currentShape.setPointsLineShape()
        setOffIcons()
        setOffMenu()
        menu?.findItem(R.id.pointsLine)?.setIcon(R.drawable.ic_points_line_on)
        menu?.findItem(R.id.pointsLineMenu)?.isChecked = true
        supportActionBar?.title = "PntsL"
    }

    private fun setCubeFrameShape() {
        currentShape.setCubeFrameShape()
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