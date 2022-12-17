package com.skapps.shoppingapp.ui.favori

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skapps.shoppingapp.data.local.localDatabase.BasketLocalDatabase
import com.skapps.shoppingapp.data.local.localDatabase.FavoriteLocalDatabase
import com.skapps.shoppingapp.data.model.Favorite

class FavoritesViewModel : ViewModel() {
    private var _favoriteList= MutableLiveData<List<Favorite>>()
    val favoriteList:LiveData<List<Favorite>>
    get() = _favoriteList
    private val favoriteLocalDatabase= FavoriteLocalDatabase()
    private val basketLocalDatabase = BasketLocalDatabase()

    fun getAllList(context: Context){
     _favoriteList.value=favoriteLocalDatabase.getAllFavorites(context)
    }

    fun delete(favorite: Favorite,context: Context){
        favoriteLocalDatabase.deleteFavorite(favorite,context)
        getAllList(context)
    }
    fun addBasketFavorite(product:Favorite,context: Context){
        product.stockQuantity=1
        basketLocalDatabase.addBasket(favoriteLocalDatabase.convertFavoriteToProduct(product),context)
    }
}