package com.skapps.shoppingapp.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skapps.shoppingapp.model.Basket
import com.skapps.shoppingapp.model.Product

class CartViewModel : ViewModel() {
    val product= Product("Harley Davidson Erkek Siyah Ayakkabı","Harley Davidson","299.99",4.6)
    val product2= Product("Adidas Erkek Siyah Ayakkabı","Adidas","499.99",4.3)
    val product3= Product("Nike Davidson Erkek Siyah Ayakkabı","Nike","679.99",4.9)
    val basket= Basket("id",product)
    val basket2= Basket("id",product2)
    val basket3= Basket("id",product3)
    val basketLis=MutableLiveData<ArrayList<Basket>>()
    val basketList=ArrayList<Basket>()
    fun getList(){
        basketList.add(basket)
        basketList.add(basket2)
        basketList.add(basket3)
        basketList.add(basket)
        basketList.add(basket3)
        basketList.add(basket2)
        basketList.add(basket)
        basketLis.value=basketList
    }

}