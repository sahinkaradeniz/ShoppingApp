package com.skapps.shoppingapp.ui.productdetails

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.local.repository.BasketRepository
import com.skapps.shoppingapp.data.local.repository.FavoriteRepository
import com.skapps.shoppingapp.data.model.Comment
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.remote.status.ApiStatus
import com.skapps.shoppingapp.data.remote.ShoppingApi
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {
    private val TAG = "ProductDetailsViewModel"
    private var _detailsProduct = MutableLiveData<Product>()
    val detailsProduct: LiveData<Product>
        get() = _detailsProduct
    private var _commentList = MutableLiveData<List<Comment>>()
    val commentList: LiveData<List<Comment>>
        get() = _commentList
    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    private val basketDatabase = BasketRepository()
    private val favoriteDatabase = FavoriteRepository()

    fun getProduct(productId: Int) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _status.value = ApiStatus.DONE
                _detailsProduct.value = ShoppingApi.retrofitService.getProduct(productId)
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                Log.e(TAG, "Api eror ${e.message.toString()}")
            }
        }
    }

    fun addBasketProduct(context: Context) {
        viewModelScope.launch {
            try {
                _detailsProduct.value?.let {
                    it.stockQuantity = 1
                    basketDatabase.addBasket(it, context)
                }
            } catch (e: Exception) {
                Log.e("Product Details ViewModel", "Add basket Product $e")
            }
        }
    }

    fun addFavoriteProdut(context: Context) {
        viewModelScope.launch {
            try {
                _detailsProduct.value?.let { favoriteDatabase.addFavorite(it, context) }
            } catch (e: Exception) {
                Log.e("Product Details ViewModel", "Add basket Product $e")
            }
        }
    }

     fun getAllCommentProduct(productId: Int) {
        viewModelScope.launch {
            try {
                _commentList.value = ShoppingApi.retrofitService.getAllCommentsProduct(productId)
            } catch (e: Exception) {
                Log.e("Product Details ViewModel", "Gel All Comments Product $e")
            }
        }
    }

}