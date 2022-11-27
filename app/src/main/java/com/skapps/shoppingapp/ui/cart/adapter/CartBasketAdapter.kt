package com.skapps.shoppingapp.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowCartDesignBinding
import com.skapps.shoppingapp.model.Basket

class CartBasketAdapter(private var basketList:ArrayList<Basket>):RecyclerView.Adapter<CartBasketAdapter.BasketViewHolder>() {
    class BasketViewHolder(private val binding:RowCartDesignBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(basket: Basket){
               binding.basketTotalPrice.text=basket.product.price
                binding.productNameBasket.text=basket.product.name
                binding.brandNameBasket.text=basket.product.brand
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