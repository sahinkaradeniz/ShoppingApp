package com.skapps.shoppingapp.ui.cart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.local.repository.BasketRepository
import com.skapps.shoppingapp.data.model.Product
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private var _basketList = MutableLiveData<List<Product>>()
    val basketList: LiveData<List<Product>> get() = _basketList
    private var _basketSize = MutableLiveData<Int>()
    val basketSize: LiveData<Int> get() = _basketSize
    private var _basketPrice = MutableLiveData<Double>()
    val basketPrice: LiveData<Double> get() = _basketPrice
    val basketRepository = BasketRepository()


    fun getList(context: Context) {
        viewModelScope.launch {
            _basketList.value = basketRepository.getAllBasket(context)
            getBasketPrice()
            getBasketSize()
        }
    }

    fun addProductCart(product: Product, context: Context) {
        viewModelScope.launch {
            basketRepository.addBasket(product, context)
            getList(context)
        }
    }

    fun deleteProduct(product: Product, context: Context) {
        viewModelScope.launch {
            basketRepository.deleteProductBasket(product, context)
            getList(context)
        }
    }

    fun minusProduct(product: Product, context: Context) {
        viewModelScope.launch {
            basketRepository.minusProductBasket(product, context)
            getList(context)
        }
    }

    private fun getBasketSize() {
        viewModelScope.launch {
            _basketSize.value =
                _basketList.value?.let { basketRepository.getBasketProductSize(it) }
        }
    }

    private fun getBasketPrice() {
        viewModelScope.launch {
            _basketPrice.value =
                _basketList.value?.let { basketRepository.getBasketTotalPrice(it) }
        }
    }

}