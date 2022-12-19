package com.skapps.shoppingapp.ui.purchaseHistory.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.data.responce.PurchaseResponce
import com.skapps.shoppingapp.databinding.RowParentHistoryPurchaseBinding

class HistoryParentAdapter(private val list: List<PurchaseResponce>) :
    RecyclerView.Adapter<HistoryParentAdapter.ParentViewHolder>() {
    class ParentViewHolder(private val binding: RowParentHistoryPurchaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(purchaseAdapterList:PurchaseResponce) {
            val childAdapter = HistoryChildAdapter(purchaseAdapterList.orderedProducts){
                val bundle = Bundle()
                bundle.putSerializable("comment",it.productId)
                Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_productDetailsFragment,
                    bundle)
            }
            binding.textView19.text = "Sipariş No : ${purchaseAdapterList.id}"
            binding.rcvHistoryParent.adapter = childAdapter
            binding.rcvHistoryParent.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val bind = RowParentHistoryPurchaseBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return ParentViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }
}