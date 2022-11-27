package com.skapps.shoppingapp.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowCategoryRightBinding
import com.skapps.shoppingapp.model.Category

class CategoryRightAdapter(private var categorylist:ArrayList<Category>):RecyclerView.Adapter<CategoryRightAdapter.RightViewHolder>(){
    class RightViewHolder(private val binding:RowCategoryRightBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(category: Category){
                binding.textCategoryRight.text=category.category
            }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RightViewHolder {
        val rcvright=RowCategoryRightBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RightViewHolder(rcvright)
    }

    override fun onBindViewHolder(holder: RightViewHolder, position: Int) {
        holder.bind(categorylist.get(position))
    }

    override fun getItemCount(): Int {
        return categorylist.size
    }
}