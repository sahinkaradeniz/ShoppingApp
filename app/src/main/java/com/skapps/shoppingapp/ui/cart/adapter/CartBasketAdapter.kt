package com.skapps.shoppingapp.ui.cart.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.RowCartDesignBinding
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.utils.customView.enums.CartClickType
import com.skapps.shoppingapp.utils.downloadImage

class CartBasketAdapter(
    private var basketList: List<Product>,
    private var onItemClick: (product: Product, click: CartClickType) -> Unit,
) : RecyclerView.Adapter<CartBasketAdapter.BasketViewHolder>() {
    class BasketViewHolder(val binding: RowCartDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            val price = "${product.price} TL"
            binding.brandNameBasket.text = product.brand
            binding.productNameBasket.text = product.model
            binding.basketTotalPrice.text = price
            binding.imageView4.downloadImage(product.imageUuid)
            binding.bvPieceText.text = product.stockQuantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val rcvBasket =
            RowCartDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(rcvBasket)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(
        holder: BasketViewHolder,
        @SuppressLint("RecyclerView") position: Int,
    ) {
        holder.bind(basketList.get(position))

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (v!!.id == R.id.delete_button) {
                    onItemClick(basketList.get(position), CartClickType.DELETE)
                }
            }

        })

        holder.binding.apply {
            imageView4.setOnClickListener {
                onItemClick(basketList.get(position),
                    CartClickType.PRODUCT)
            }
            bvAddButton.setOnClickListener {
                onItemClick(basketList.get(position), CartClickType.ADD)
            }
            bvDeleteButton.setOnClickListener {
                onItemClick(basketList.get(position), CartClickType.REDUCTION)
            }
            deleteButton.setOnClickListener {   onItemClick(basketList.get(position), CartClickType.DELETE) }
        }
    }

    override fun getItemCount(): Int {
        return basketList.size
    }
}