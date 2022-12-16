package com.skapps.shoppingapp.ui.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.model.Category
import com.skapps.shoppingapp.data.remote.ShoppingApi
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    private  var _products=MutableLiveData<Category>()
    val products:LiveData<Category> get() = _products
    private val TAG="Products Vİew Model"

    fun getCategoryProducts(categoryId:Int){
        viewModelScope.launch {
            try {
                _products.value=ShoppingApi.retrofitService.getCategoryProducts(categoryId)
            }catch (e:Exception){
                Log.e(TAG,e.message.toString())
            }

        }
    }
}