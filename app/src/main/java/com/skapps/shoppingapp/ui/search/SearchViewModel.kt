package com.skapps.shoppingapp.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.local.SearchLocalDatabase
import com.skapps.shoppingapp.data.model.Search
import kotlinx.coroutines.launch

class SearchViewModel(): ViewModel() {
    private var _searchHistory=MutableLiveData<List<Search>>()
    val searchHistory:LiveData<List<Search>> get() = _searchHistory
    private val searchLocalDatabase=SearchLocalDatabase()

  

}