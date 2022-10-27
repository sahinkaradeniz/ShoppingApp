package com.skapps.shoppingapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.adapter.SearchHistoryRcvAdapter
import com.skapps.shoppingapp.databinding.FragmentSearchBinding
import com.skapps.shoppingapp.model.SearchHistory

class SearchFragment : Fragment() {

    private lateinit var binding:FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchHistoryRcvAdapter:SearchHistoryRcvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val history=SearchHistory("1","Ceket")
        val history2=SearchHistory("1","Ayakkabı ipi")
        val historyList=ArrayList<SearchHistory>()
        historyList.add(history)
        historyList.add(history2)
        historyList.add(history)
        historyList.add(history2)
        historyList.add(history)
        binding.rcvSearchHistory.apply {
            searchHistoryRcvAdapter=SearchHistoryRcvAdapter(historyList)
            layoutManager=LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
            adapter=searchHistoryRcvAdapter
        }
    }

}