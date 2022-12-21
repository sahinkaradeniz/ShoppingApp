package com.skapps.shoppingapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowSearchProductBinding
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.utils.convertPricetoTL
import com.skapps.shoppingapp.utils.downloadImage
import com.skapps.shoppingapp.utils.enums.HomeClickType
import com.skapps.shoppingapp.utils.averatingformat1F

class SearchProductAdapter( var productList:List<Product>,val onItemClick : (product:Product,clickType:HomeClickType) -> Unit):RecyclerView.Adapter<SearchProductAdapter.ProductViewHolder>() {
    class ProductViewHolder( val binding:RowSearchProductBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.searchTextProductName.text= product.model
            binding.searchTextRate.text= product.averageRating?.averatingformat1F()
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
          onClick(holder,position)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    private fun onClick(holder: ProductViewHolder,position:Int){
        holder.binding.productFav.setOnClickListener {
            onItemClick(productList.get(position),HomeClickType.FAVORİ)
        }
        holder.binding.searchBuyButton.setOnClickListener {
            onItemClick(productList.get(position),HomeClickType.BUYBUTTON)
        }
        holder.binding.searchProductImage.setOnClickListener {
            onItemClick(productList.get(position),HomeClickType.IMAGE)
        }
    }
}