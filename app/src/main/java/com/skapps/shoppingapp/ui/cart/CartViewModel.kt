package com.skapps.shoppingapp.ui.cart

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.local.BasketLocalDatabase
import com.skapps.shoppingapp.data.model.Basket
import com.skapps.shoppingapp.data.model.Product
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    val basketLis=MutableLiveData<List<Product>>()
    val localDatabase=BasketLocalDatabase()
    fun getList(context: Context):List<Product>{
       viewModelScope.launch {
          basketLis.value= localDatabase.getAllBasket(context)
       }
      return arrayListOf()
    }

}