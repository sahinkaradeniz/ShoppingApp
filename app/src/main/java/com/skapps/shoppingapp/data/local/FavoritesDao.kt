package com.skapps.shoppingapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.skapps.shoppingapp.data.model.Product

@Dao
interface FavoritesDao {
    @Insert
    fun addFavorite(product: Product)
    @Query("Select * from  favorite")
    fun getAllFavorites():List<Product>
    @Delete
    fun deleteFavorite(product: Product)
}