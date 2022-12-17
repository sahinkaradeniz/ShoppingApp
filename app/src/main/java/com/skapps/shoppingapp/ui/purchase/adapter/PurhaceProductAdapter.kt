package com.skapps.shoppingapp.ui.purchase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.databinding.RowCartDesignBinding
import com.skapps.shoppingapp.utils.downloadImage

class PurhaceProductAdapter(val productsList: List<Product>) :
    RecyclerView.Adapter<PurhaceProductAdapter.PurchaseViewHolder>() {
    class PurchaseViewHolder(val binding: RowCartDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            val price = "${product.price} TL"
            binding.brandNameBasket.text = product.brand
            binding.productNameBasket.text = product.description
            binding.basketTotalPrice.text = price
            binding.imageView4.downloadImage(product.imageUuid)
            binding.bvPieceText.text = product.stockQuantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val binding=RowCartDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PurchaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        holder.bind(productsList.get(position))
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}