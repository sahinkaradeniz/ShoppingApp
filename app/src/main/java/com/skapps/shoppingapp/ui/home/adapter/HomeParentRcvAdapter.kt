package com.skapps.shoppingapp.ui.home.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.data.local.repository.BasketRepository
import com.skapps.shoppingapp.data.local.repository.FavoriteRepository
import com.skapps.shoppingapp.data.model.Category
import com.skapps.shoppingapp.databinding.RowParentHomeBinding
import com.skapps.shoppingapp.utils.enums.HomeClickType
import com.skapps.shoppingapp.utils.succesBasketToast
import com.skapps.shoppingapp.utils.succesFavoriteToast

class HomeParentRcvAdapter(private var productList: List<Category>,private var onItemClick: (category:Category,click: HomeClickType) -> Unit) :
    RecyclerView.Adapter<HomeParentRcvAdapter.HomeParentViewHolder>() {

    class HomeParentViewHolder(val binding: RowParentHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            val basketRepository= BasketRepository()
            val favoriteRepository= FavoriteRepository()
            binding.parentText.text = category.categoryName
            val childRcvAdapter = HomeChildRcvAdapter(category.products) { product, click ->
                when (click) {
                    HomeClickType.BUYBUTTON ->{
                        product.stockQuantity=1
                        basketRepository.addBasket(product,binding.root.context)
                        binding.root.context.succesBasketToast()
                    }
                    HomeClickType.IMAGE -> {
                        val bundle = Bundle()
                        bundle.putSerializable("prod", product.id)
                        findNavController(binding.root).navigate(R.id.action_homeFragment_to_productDetailsFragment,
                            bundle)
                    }
                    HomeClickType.FAVORİ ->{
                        binding.root.context.succesFavoriteToast()
                        favoriteRepository.addFavorite(product,binding.root.context)
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
        holder.binding.apply {
            buttonCategory.setOnClickListener {
                onItemClick(productList.get(position),HomeClickType.CATEGORY)
            }
            textCategory.setOnClickListener {
                onItemClick(productList.get(position),HomeClickType.CATEGORY)
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}