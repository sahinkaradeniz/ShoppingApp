package com.skapps.shoppingapp.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.base.BaseViewModel
import com.skapps.shoppingapp.data.model.Category
import com.skapps.shoppingapp.data.remote.status.ApiStatus
import com.skapps.shoppingapp.data.remote.ShoppingApi
import kotlinx.coroutines.launch


class HomeViewModel(application: Application):BaseViewModel(application) {
    private var _productList=MutableLiveData<List<Category>>()
    val products:LiveData<List<Category>> get() = _productList

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private fun getAllProductList(){
        viewModelScope.launch {
            _status.value= ApiStatus.LOADING
            try {
               _productList.value=ShoppingApi.retrofitService.getAllCategory()
                _status.value= ApiStatus.DONE
            }catch (e : Exception){
                _status.value= ApiStatus.ERROR
                _productList.value= ArrayList()
                Log.e("AllProd",e.message.toString())
            }
        }
    }
    init {
        getAllProductList()
    }

}
