package com.skapps.shoppingapp.ui.cart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.local.BasketDatabase
import com.skapps.shoppingapp.data.local.BasketLocalDatabase
import com.skapps.shoppingapp.data.model.Basket
import com.skapps.shoppingapp.data.model.Product
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

   private var _basketList=MutableLiveData<List<Product>>()
   val basketList:LiveData<List<Product>>
    get() = _basketList
    val localDatabase=BasketLocalDatabase()
    val basketLocalDatabase=BasketLocalDatabase()
    fun getList(context: Context){
       viewModelScope.launch {
           _basketList.value= localDatabase.getAllBasket(context)
       }
    }
    fun addProductCart(product: Product,context: Context){
        basketLocalDatabase.addBasket(product,context)
        getList(context)
    }
    fun deleteProduct(product: Product,context: Context){
            basketLocalDatabase.deleteProductBasket(product,context)
            getList(context)
    }
    fun minusProduct(product: Product,context: Context){
            basketLocalDatabase.minusProductBasket(product,context)
            getList(context)
    }

}