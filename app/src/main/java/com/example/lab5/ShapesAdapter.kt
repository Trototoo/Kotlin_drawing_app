package com.example.lab5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5.MainActivity.Companion.utilities
import com.example.lab5.databinding.ShapeItemBinding
import com.example.lab5.shapes.Shape

class ShapesAdapter: RecyclerView.Adapter<ShapesAdapter.ShapeHolder>() {

    class ShapeHolder(item: View): RecyclerView.ViewHolder(item) {

        val btn = item.findViewById<Button>(R.id.deleteButton)

        val binding = ShapeItemBinding.bind(item)
        fun bind(shape: Shape) = with (binding){
            ShapeNameText.text = shape.getName()
            val startX = shape.startX.toInt()
            val startY = shape.startY.toInt()
            val currentX = shape.currentX.toInt()
            val currentY = shape.currentY.toInt()
            if (shape.getType() != ShapeEnum.POINT) {
                CordText.text = "($startX;$startY) -> ($currentX;$currentY)"
            } else {
                CordText.text = "($startX;$startY)"
            }
            selectCheckBox.isChecked = shape.selected
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShapeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shape_item, parent, false)
        return ShapeHolder(view)
    }

    override fun onBindViewHolder(holder: ShapeHolder, position: Int) {
        holder.bind(utilities.dynamicArrayOfShape[holder.adapterPosition])
        val checkBox = holder.binding.selectCheckBox
        checkBox.setOnClickListener {
            utilities.dynamicArrayOfShape[holder.adapterPosition].selected = !utilities.dynamicArrayOfShape[holder.adapterPosition].selected
            checkBox.isChecked = utilities.dynamicArrayOfShape[holder.adapterPosition].selected
        }

        holder.btn.setOnClickListener {
            utilities.dynamicArrayOfShape.removeAt(holder.adapterPosition)
            utilities.decrementIndex()
            notifyItemRemoved(holder.adapterPosition)
            utilities.resetTableFile()
        }
    }

    override fun getItemCount(): Int {
        return utilities.dynamicArrayOfShape.size
    }
}