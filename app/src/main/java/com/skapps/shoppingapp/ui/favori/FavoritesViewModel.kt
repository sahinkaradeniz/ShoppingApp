package com.skapps.shoppingapp.ui.favori

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skapps.shoppingapp.data.local.repository.BasketRepository
import com.skapps.shoppingapp.data.local.repository.FavoriteRepository
import com.skapps.shoppingapp.data.model.Favorite

class FavoritesViewModel : ViewModel() {
    private var _favoriteList= MutableLiveData<List<Favorite>>()
    val favoriteList:LiveData<List<Favorite>>
    get() = _favoriteList
    private val favoriteRepository= FavoriteRepository()
    private val basketRepository = BasketRepository()

    fun getAllList(context: Context){
     _favoriteList.value=favoriteRepository.getAllFavorites(context)
    }

    fun delete(favorite: Favorite,context: Context){
        favoriteRepository.deleteFavorite(favorite,context)
        getAllList(context)
    }
    fun addBasketFavorite(product:Favorite,context: Context){
        product.stockQuantity=1
        basketRepository.addBasket(favoriteRepository.convertFavoriteToProduct(product),context)
    }
}