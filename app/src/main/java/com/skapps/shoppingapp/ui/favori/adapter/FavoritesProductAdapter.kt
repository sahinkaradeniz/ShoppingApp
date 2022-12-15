package com.skapps.shoppingapp.ui.favori.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.data.model.Favorite
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.databinding.RowFavoritesCardBinding
import com.skapps.shoppingapp.utils.customView.enums.FavoriteClickType
import com.skapps.shoppingapp.utils.customView.enums.HomeClickType
import com.skapps.shoppingapp.utils.downloadImage

class FavoritesProductAdapter(private val productList:List<Favorite>, var onItemClick: (product:Favorite, click:FavoriteClickType) -> Unit):RecyclerView.Adapter<FavoritesProductAdapter.FavoritesViewHolder>() {
    class FavoritesViewHolder(val binding: RowFavoritesCardBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product:Favorite){
            binding.apply {
                val price = "${product.price} TL"
                binding.textPrice.text = price
                binding.textProductName.text = product.model
                binding.textRate.text = product.averageRating.toString()
                binding.productImage.downloadImage(product.imageUuid)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val cardBinding=RowFavoritesCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoritesViewHolder(cardBinding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
      holder.bind(productList.get(position))
        holder.binding.apply {
            productDelete.setOnClickListener {
                onItemClick(productList.get(position),FavoriteClickType.DELETE)
            }
            buyButton.setOnClickListener {
                onItemClick(productList.get(position),FavoriteClickType.BUY)
            }
            productImage.setOnClickListener {
                onItemClick(productList.get(position),FavoriteClickType.PROCUDT)
            }
        }


    }

    override fun getItemCount(): Int {
       return productList.size
    }
}