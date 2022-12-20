package com.skapps.shoppingapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowChildHomeBinding
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.utils.convertPricetoTL
import com.skapps.shoppingapp.utils.enums.HomeClickType
import com.skapps.shoppingapp.utils.downloadImage

class HomeChildRcvAdapter(
    val productsList: List<Product>,
    private var onItemClick: (product: Product, click: HomeClickType) -> Unit,
) : RecyclerView.Adapter<HomeChildRcvAdapter.HomeViewHolder>() {

    class HomeViewHolder(val binding: RowChildHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.textPrice.text = product.price?.convertPricetoTL()
            binding.textProductName.text = product.model
            binding.textRate.text = product.averageRating.toString()
            binding.productImage.downloadImage(product.imageUuid)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val homeRCVBinding =
            RowChildHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(homeRCVBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(productsList.get(position))
        holder.binding.apply {
            buyButton.setOnClickListener {
                onItemClick(productsList.get(position),
                    HomeClickType.BUYBUTTON)
            }
            productImage.setOnClickListener {
                onItemClick(productsList.get(position),
                    HomeClickType.IMAGE)
            }
            cardView2.setOnClickListener {
                onItemClick(productsList.get(position),
                    HomeClickType.CARD)
            }
            productFav.setOnClickListener {
                onItemClick(productsList.get(position),
                    HomeClickType.FAVORİ)
            }

        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}