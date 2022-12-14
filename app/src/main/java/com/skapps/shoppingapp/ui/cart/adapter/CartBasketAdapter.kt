package com.skapps.shoppingapp.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowCartDesignBinding
import com.skapps.shoppingapp.data.model.Basket
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.utils.downloadImage

class CartBasketAdapter(private var basketList: List<Product>):RecyclerView.Adapter<CartBasketAdapter.BasketViewHolder>() {
    class BasketViewHolder(private val binding:RowCartDesignBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(product:Product){
                val price = "${product.price} TL"
                binding.brandNameBasket.text=product.brand
                binding.productNameBasket.text=product.model
                binding.basketTotalPrice.text= price
                binding.imageView4.downloadImage(product.imageUuid)
                product.stockQuantity?.let { binding.basketView.setPiece(it) }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val rcvBasket=RowCartDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BasketViewHolder(rcvBasket)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(basketList.get(position))
    }

    override fun getItemCount(): Int {
        return basketList.size
    }
}