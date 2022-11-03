package com.example.lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab5.databinding.ActivityShapesTableBinding
import com.example.lab5.shapes.Shape

class ShapesTableActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShapesTableBinding
    lateinit var adapter: ShapesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShapesTableBinding.inflate(layoutInflater)

        adapter = ShapesAdapter()

        setContentView(binding.root)

        init()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Shapes Table"
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@ShapesTableActivity)
            rcView.adapter = adapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed();
        return true
    }

}