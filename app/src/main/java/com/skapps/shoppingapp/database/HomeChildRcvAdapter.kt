package com.skapps.shoppingapp.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowChildHomeBinding
import com.skapps.shoppingapp.model.Product
import com.skapps.shoppingapp.model.ProductModel

class HomeChildRcvAdapter(val productsList: List<Product>):RecyclerView.Adapter<HomeChildRcvAdapter.HomeViewHolder>(){
    class HomeViewHolder(private val binding:RowChildHomeBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(product:Product){
            val price ="${product.price} TL"
            binding.textPrice.text=price
            binding.textProductName.text=product.name
            binding.textRate.text=product.stars.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val homeRCVBinding=RowChildHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(homeRCVBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(productsList.get(position))
    }

    override fun getItemCount(): Int {
       return productsList.size
    }

}