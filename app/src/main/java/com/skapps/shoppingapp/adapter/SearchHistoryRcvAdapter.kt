package com.skapps.shoppingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowHistorySearchBinding
import com.skapps.shoppingapp.model.SearchHistory

class SearchHistoryRcvAdapter(private var productList:ArrayList<SearchHistory>):RecyclerView.Adapter<SearchHistoryRcvAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(private val binding:RowHistorySearchBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(search:SearchHistory){
            binding.searchProductName.text=search.searchText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val rcvHistory=RowHistorySearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HistoryViewHolder(rcvHistory)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(productList.get(position))
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}