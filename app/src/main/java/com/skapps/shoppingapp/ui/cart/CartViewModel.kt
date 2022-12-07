package com.skapps.shoppingapp.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skapps.shoppingapp.model.Basket
import com.skapps.shoppingapp.model.Product

class CartViewModel : ViewModel() {

    val basketLis=MutableLiveData<ArrayList<Basket>>()
    fun getList(){

    }

}