package com.skapps.shoppingapp.ui.favori.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.databinding.FragmentFavoriBinding
import com.skapps.shoppingapp.databinding.RowFavoritesCardBinding

class FavoritesProductAdapter(private val productList:List<Product>):RecyclerView.Adapter<FavoritesProductAdapter.FavoritesViewHolder>() {
    class FavoritesViewHolder(val binding: RowFavoritesCardBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val cardBinding=RowFavoritesCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoritesViewHolder(cardBinding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
      holder.bind(productList.get(position))
    }

    override fun getItemCount(): Int {
       return productList.size
    }
}