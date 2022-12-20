package com.skapps.shoppingapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowSearchProductBinding
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.utils.convertPricetoTL
import com.skapps.shoppingapp.utils.downloadImage

class SearchProductAdapter(private var productList:List<Product>):RecyclerView.Adapter<SearchProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(private val binding:RowSearchProductBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.searchTextProductName.text= product.model
            binding.searchTextRate.text= product.averageRating.toString()
            binding.searchTextPrice.text = product.price?.convertPricetoTL()
            binding.searchProductImage.downloadImage(product.imageUuid)
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