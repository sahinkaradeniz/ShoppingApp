package com.skapps.shoppingapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.skapps.shoppingapp.data.model.Favorite
import com.skapps.shoppingapp.data.model.Product

@Dao
interface FavoritesDao {
    @Insert
    fun addFavorite(product: Favorite)
    @Query("Select * from  favorite")
    fun getAllFavorites():List<Favorite>
    @Delete
    fun deleteFavorite(product:Favorite)
}