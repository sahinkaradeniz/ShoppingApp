package com.skapps.shoppingapp.ui.home.adapter

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.data.local.BasketDatabase
import com.skapps.shoppingapp.data.local.BasketLocalDatabase
import com.skapps.shoppingapp.data.local.FavoriteLocalDatabase
import com.skapps.shoppingapp.data.model.Category
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.databinding.RowParentHomeBinding
import com.skapps.shoppingapp.data.model.ProductModel
import com.skapps.shoppingapp.ui.home.HomeFragmentDirections
import com.skapps.shoppingapp.utils.HomeClickType
import com.skapps.shoppingapp.utils.succesToast
import com.skapps.shoppingapp.utils.toast

class HomeParentRcvAdapter(private var productList: List<Category>) :
    RecyclerView.Adapter<HomeParentRcvAdapter.HomeParentViewHolder>() {

    class HomeParentViewHolder(private val binding: RowParentHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            val basketLocalDatabase=BasketLocalDatabase()
            val favoriteLocalDatabase=FavoriteLocalDatabase()
            binding.parentText.text = category.categoryName
            val childRcvAdapter = HomeChildRcvAdapter(category.products) { product, click ->
                when (click) {
                    HomeClickType.BUYBUTTON ->{
                        product.stockQuantity=1
                        basketLocalDatabase.addBasket(product,binding.root.context)
                        binding.root.context.succesToast("Ürün Sepete Eklendi")
                    }
                    HomeClickType.IMAGE -> {
                        val bundle = Bundle()
                        bundle.putSerializable("prod", product.id)
                        findNavController(binding.root).navigate(R.id.action_homeFragment_to_productDetailsFragment,
                            bundle)
                    }
                    HomeClickType.FAVORİ ->{
                        binding.root.context.succesToast("Favorilere Eklendi")
                        favoriteLocalDatabase.addFavorite(product,binding.root.context)
                    }
                    else -> {
                        val bundle = Bundle()
                        bundle.putSerializable("prod", product.id)
                        findNavController(binding.root).navigate(R.id.action_homeFragment_to_productDetailsFragment,
                            bundle)
                    }
                }
            }
            binding.parentRcv.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.parentRcv.adapter = childRcvAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeParentViewHolder {
        val rcvParent =
            RowParentHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeParentViewHolder(rcvParent)
    }

    override fun onBindViewHolder(holder: HomeParentViewHolder, position: Int) {
        holder.bind(productList.get(position))
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}