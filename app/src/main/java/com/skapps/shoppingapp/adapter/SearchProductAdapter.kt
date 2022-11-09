package com.skapps.shoppingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowChildHomeBinding
import com.skapps.shoppingapp.databinding.RowSearchProductBinding
import com.skapps.shoppingapp.model.Product

class SearchProductAdapter(private var productList:ArrayList<Product>):RecyclerView.Adapter<SearchProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(private val binding:RowSearchProductBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            val price ="${product.price} TL"
            binding.searchTextPrice.text=price
            binding.searchTextProductName.text=product.name
            binding.searchTextRate.text=product.stars.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       val rcvProduct=RowSearchProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(rcvProduct)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
          holder.bind(productList.get(position))
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}