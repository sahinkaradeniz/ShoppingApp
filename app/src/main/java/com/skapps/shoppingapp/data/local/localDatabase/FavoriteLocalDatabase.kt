package com.skapps.shoppingapp.data.local.localDatabase

import android.content.Context
import android.util.Log
import com.skapps.shoppingapp.data.local.database.FavoriteDatabase
import com.skapps.shoppingapp.data.model.Favorite
import com.skapps.shoppingapp.data.model.Product

class FavoriteLocalDatabase {
    private var TAG="FavoriteLocalDatabase"
    private lateinit var favoriteDatabase: FavoriteDatabase

    fun addFavorite(product: Product, context: Context){
        try {
            favoriteDatabase= FavoriteDatabase.getFavoriteDatabase(context)!!
            favoriteDatabase.getFavoriteDao().addFavorite(convertProductToFavorite(product))
        }catch (e:Exception){
            Log.e(TAG,e.message.toString())
        }
    }
    fun deleteFavorite(product:Favorite,context: Context){
        try {
            favoriteDatabase= FavoriteDatabase.getFavoriteDatabase(context)!!
            favoriteDatabase.getFavoriteDao().deleteFavorite(product)
        }catch (e:Exception){
            Log.e(TAG,e.message.toString())
        }
    }
    fun getAllFavorites(context: Context):List<Favorite>{
        try {
            favoriteDatabase= FavoriteDatabase.getFavoriteDatabase(context)!!
           return favoriteDatabase.getFavoriteDao().getAllFavorites()
        }catch (e:Exception){
            Log.e(TAG,e.message.toString())
            return arrayListOf()
        }
    }
    private fun convertProductToFavorite(product: Product):Favorite{
        val favorite=Favorite(product.id,product.brand,product.model,product.shortDescription,product.description,product.price,product.stockQuantity,product.averageRating,product.imageUuid,product.categoryId,product.status)
        return favorite
    }
    fun convertFavoriteToProduct(product: Favorite):Product{
        val product=Product(product.id,product.brand,product.model,product.shortDescription,product.description,product.price,product.stockQuantity,product.averageRating,product.imageUuid,product.categoryId,product.status)
        return product
    }
}