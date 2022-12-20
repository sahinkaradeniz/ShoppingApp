package com.skapps.shoppingapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.data.model.Search
import com.skapps.shoppingapp.databinding.RowHistorySearchBinding
import com.skapps.shoppingapp.utils.enums.HistoryClickType

class SearchHistoryRcvAdapter(private var searchList:List<Search>,var onItemClick:(search:Search,click:HistoryClickType) -> Unit):RecyclerView.Adapter<SearchHistoryRcvAdapter.HistoryViewHolder>() {
    class HistoryViewHolder( val binding:RowHistorySearchBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(search:Search){
            binding.searchProductName.text=search.search
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val rcvHistory=RowHistorySearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HistoryViewHolder(rcvHistory)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(searchList.get(position))
        holder.binding.searchHistoryClose.setOnClickListener {
            onItemClick(searchList.get(position),HistoryClickType.Delete)
        }
        holder.binding.searchProductName.setOnClickListener{
            onItemClick(searchList.get(position),HistoryClickType.Search)
        }
    }

    override fun getItemCount(): Int {
        return searchList.size
    }
}