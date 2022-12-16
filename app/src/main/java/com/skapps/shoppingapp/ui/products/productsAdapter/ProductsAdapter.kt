package com.skapps.shoppingapp.ui.products.productsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.databinding.RowProductCardBinding
import com.skapps.shoppingapp.utils.convertPricetoTL
import com.skapps.shoppingapp.utils.customView.enums.FavoriteClickType
import com.skapps.shoppingapp.utils.downloadImage

class ProductsAdapter(
    private val productList: List<Product>,
    var onItemClick: (product: Product, click: FavoriteClickType) -> Unit,
) : RecyclerView.Adapter<ProductsAdapter.FavoritesViewHolder>() {
    class FavoritesViewHolder(val binding: RowProductCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                binding.textPrice.text = product.price?.convertPricetoTL()
                binding.textProductName.text = product.model
                binding.textRate.text = product.averageRating.toString()
                binding.productImage.downloadImage(product.imageUuid)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val cardBinding =
            RowProductCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(cardBinding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(productList.get(position))
        holder.binding.apply {
            buyButton.setOnClickListener {
                onItemClick(productList.get(position), FavoriteClickType.BUY)
            }
            productFavorite.setOnClickListener {
                onItemClick(productList.get(position), FavoriteClickType.FAVORİTE)
            }
            productImage.setOnClickListener {
                onItemClick(productList.get(position), FavoriteClickType.PROCUDT)
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}