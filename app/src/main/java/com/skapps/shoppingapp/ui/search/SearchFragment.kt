package com.skapps.shoppingapp.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.adapter.SearchHistoryRcvAdapter
import com.skapps.shoppingapp.adapter.SearchProductAdapter
import com.skapps.shoppingapp.databinding.FragmentSearchBinding
import com.skapps.shoppingapp.model.Product
import com.skapps.shoppingapp.model.SearchHistory

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchHistoryRcvAdapter: SearchHistoryRcvAdapter
    private lateinit var searchProductAdapter: SearchProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val history = SearchHistory("1", "Ceket")
        val history2 = SearchHistory("1", "Ayakkabı ipi")
        val historyList = ArrayList<SearchHistory>()
        historyList.add(history)
        historyList.add(history2)
        historyList.add(history)
        historyList.add(history2)
        historyList.add(history)
        binding.rcvSearchHistory.apply {
            searchHistoryRcvAdapter = SearchHistoryRcvAdapter(historyList)
            layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = searchHistoryRcvAdapter
        }
        val product =
            Product(getString(R.string.test_string_ayakkabi), "Harley Davidson", "299.99", 4.6)
        val productList = ArrayList<Product>()
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        binding.rcvSearchProduct.apply {
            searchProductAdapter = SearchProductAdapter(productList)
            layoutManager = GridLayoutManager(binding.root.context, 2)
            adapter = searchProductAdapter
        }
        binding.backHome.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_productDetailsFragment2)
        }

    }
}