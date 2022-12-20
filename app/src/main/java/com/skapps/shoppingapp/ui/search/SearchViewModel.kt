package com.skapps.shoppingapp.ui.search

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.local.repository.SearchRepository
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.model.Search
import com.skapps.shoppingapp.data.remote.ShoppingApi
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private var _searchHistory=MutableLiveData<List<Search>>()
    val searchHistory:LiveData<List<Search>> get() = _searchHistory
    private val searchRepository= SearchRepository()
    private var _productList=MutableLiveData<List<Product>>()
    val productList:LiveData<List<Product>> get() = _productList
    private val TAG="Search view model "
    private var _searchSize =MutableLiveData<Int>()
    val searchSize:LiveData<Int> get() = _searchSize

    fun gellAllHistory(context: Context){
       viewModelScope.launch {
         _searchHistory.value=searchRepository.getAllSearchHistory(context)
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
    fun searchProduct(text:String){
        viewModelScope.launch {
            try {
                _productList.value=ShoppingApi.retrofitService.elasticSearchProduct(text)
                _searchSize.value=_productList.value?.size
            }catch (e:Exception){
                Log.e(TAG,"search product exception : $e")
            }
        }
    }
}