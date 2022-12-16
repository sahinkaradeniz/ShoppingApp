package com.skapps.shoppingapp.ui.products

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.local.BasketLocalDatabase
import com.skapps.shoppingapp.data.local.FavoriteLocalDatabase
import com.skapps.shoppingapp.data.model.Category
import com.skapps.shoppingapp.data.model.Favorite
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.remote.ShoppingApi
import com.skapps.shoppingapp.utils.succesToast
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    private  var _products=MutableLiveData<Category>()
    val products:LiveData<Category> get() = _products
    private val TAG="Products Vİew Model"
    val basketLocalDatabase= BasketLocalDatabase()
    private val favoriteLocalDatabase= FavoriteLocalDatabase()
    fun getCategoryProducts(categoryId:Int){
        viewModelScope.launch {
            try {
                _products.value=ShoppingApi.retrofitService.getCategoryProducts(categoryId)
            }catch (e:Exception){
                Log.e(TAG,e.message.toString())
            }

        }
    }
    fun addBasketProduct(product:Product,context: Context){
        product.stockQuantity=1
        basketLocalDatabase.addBasket(product,context)
        context.succesToast("Ürün Sepete Eklendi")
    }
    fun addFavoriteProduct(product: Product,context: Context){
        context.succesToast("Favorilere Eklendi")
        favoriteLocalDatabase.addFavorite(product,context)
    }
}