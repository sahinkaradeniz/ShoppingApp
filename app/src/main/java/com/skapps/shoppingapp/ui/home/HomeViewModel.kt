package com.skapps.shoppingapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.model.ProductModel
import com.skapps.shoppingapp.data.remote.ShoppingApi
import com.skapps.shoppingapp.data.remote.ShoppingApiService
import com.skapps.shoppingapp.data.remote.responce.ProductResponce
import kotlinx.coroutines.launch

enum class ApiStatus{ LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {
    private var _productList=MutableLiveData<ArrayList<Product>>()
    val products:LiveData<ArrayList<Product>> get() = _productList

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private fun getAllProductList(){
        viewModelScope.launch {
            _status.value
            try {
                _productList.value =ShoppingApi.retrofitService.getAllProduct().product
                Log.e("AllProd",_productList.toString())
            }catch (e : Exception){
                _status.value=ApiStatus.ERROR
                _productList.value= ArrayList()
                Log.e("AllProd",e.message.toString())
            }
        }
    }
    init {
        getAllProductList()
    }



}
