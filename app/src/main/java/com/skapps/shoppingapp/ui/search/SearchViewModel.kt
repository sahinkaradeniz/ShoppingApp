package com.skapps.shoppingapp.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.local.repository.SearchRepository
import com.skapps.shoppingapp.data.model.Search
import kotlinx.coroutines.launch

class SearchViewModel(): ViewModel() {
    private var _searchHistory=MutableLiveData<List<Search>>()
    val searchHistory:LiveData<List<Search>> get() = _searchHistory
    private val searchRepository= SearchRepository()

    fun gellAllHistory(context: Context){
       viewModelScope.launch {
         _searchHistory.value=  searchRepository.getAllSearchHistory(context)
       }
    }

    fun addSearch(searchText:String,context: Context){
        viewModelScope.launch {
            val search=Search(searchText)
           searchRepository.addSearch(search, context)
        }
    }

    fun deleteSearch(search: Search,context: Context){
        viewModelScope.launch {
            searchRepository.deleteSearch(search, context)
        }
    }
}