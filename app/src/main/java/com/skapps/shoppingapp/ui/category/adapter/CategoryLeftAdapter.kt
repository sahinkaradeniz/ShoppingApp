package com.skapps.shoppingapp.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowCategoryLeftBinding
import com.skapps.shoppingapp.data.model.Category
import com.skapps.shoppingapp.utils.downloadImage

class CategoryLeftAdapter(private var categoryList:List<Category>,var onItemClick: (category:Category) -> Unit):RecyclerView.Adapter<CategoryLeftAdapter.LeftViewHolder>() {
    class LeftViewHolder( val binding:RowCategoryLeftBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(category: Category){
            binding.textCategoryLeft.text=category.categoryName
            binding.imageView3.downloadImage(category.imageUuid)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeftViewHolder {
        val rcvLeftBinding=RowCategoryLeftBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LeftViewHolder(rcvLeftBinding)
    }

    override fun onBindViewHolder(holder: LeftViewHolder, position: Int) {
        holder.bind(categoryList.get (position))
        holder.itemView.setOnClickListener {
            onItemClick(categoryList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}