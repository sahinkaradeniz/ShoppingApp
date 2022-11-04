package com.skapps.shoppingapp.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skapps.shoppingapp.model.Basket
import com.skapps.shoppingapp.model.Product

class CartViewModel : ViewModel() {
    val product= Product("(R.string.test_string_ayakkabi)","Harley Davidson","299.99",4.6)
    val basket= Basket("id",product)
    val basketLis=MutableLiveData<ArrayList<Basket>>()
    val basketList=ArrayList<Basket>()
    fun getList(){
        basketList.add(basket)
        basketList.add(basket)
        basketList.add(basket)
        basketList.add(basket)
        basketList.add(basket)
        basketList.add(basket)
        basketList.add(basket)
        basketLis.value=basketList
    }

}