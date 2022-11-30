package com.skapps.shoppingapp.ui.home.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.RowParentHomeBinding
import com.skapps.shoppingapp.model.Product
import com.skapps.shoppingapp.model.ProductModel

class HomeParentRcvAdapter(private var productList:List<ProductModel>):RecyclerView.Adapter<HomeParentRcvAdapter.HomeParentViewHolder>() {

    class HomeParentViewHolder(private val binding: RowParentHomeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product: ProductModel){
            binding.parentText.text=product.title
            val childRcvAdapter= HomeChildRcvAdapter(product.productModel){
                val bundle = Bundle()
                bundle.putSerializable("product",it)
                findNavController(binding.root).navigate(R.id.action_homeFragment_to_productDetailsFragment,
                    bundle)
            }
            binding.parentRcv.layoutManager= LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL,false)
            binding.parentRcv.adapter=childRcvAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeParentViewHolder {
        val rcvParent=RowParentHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeParentViewHolder(rcvParent)
    }

    override fun onBindViewHolder(holder: HomeParentViewHolder, position: Int) {
        holder.bind(productList.get(position))
    }

    override fun getItemCount(): Int {
      return  productList.size
    }
}