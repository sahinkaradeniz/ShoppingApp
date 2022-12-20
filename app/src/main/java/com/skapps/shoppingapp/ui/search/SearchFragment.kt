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
import com.skapps.shoppingapp.ui.search.adapter.SearchHistoryRcvAdapter
import com.skapps.shoppingapp.ui.search.adapter.SearchProductAdapter
import com.skapps.shoppingapp.databinding.FragmentSearchBinding
import com.skapps.shoppingapp.utils.enums.HistoryClickType

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchHistoryRcvAdapter: SearchHistoryRcvAdapter
    private lateinit var searchProductAdapter: SearchProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        observeLiveData()
        viewModel.gellAllHistory(requireContext())
        binding.backHome.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.input.setStartIconOnClickListener {
            val searchText = binding.input.editText?.text.toString()
            viewModel.addSearch(searchText, requireContext())
            viewModel.gellAllHistory(requireContext())
        }

        binding.rcvSearchProduct.apply {
            searchProductAdapter = SearchProductAdapter(arrayListOf())
            layoutManager = GridLayoutManager(binding.root.context, 2)
            adapter = searchProductAdapter
        }
    }

    private fun observeLiveData() {
        viewModel.searchHistory.observe(viewLifecycleOwner) { historyList ->
            binding.rcvSearchHistory.apply {
                searchHistoryRcvAdapter = SearchHistoryRcvAdapter(historyList){ search ,click ->
                    when(click){
                        HistoryClickType.Search -> {

                        }
                        HistoryClickType.Delete -> {
                            viewModel.deleteSearch(search,requireContext())
                            viewModel.gellAllHistory(requireContext())
                        }
                    }
                }
                layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = searchHistoryRcvAdapter
            }
        }
    }

}