package com.skapps.shoppingapp.data.local.dao

import androidx.room.*
import com.skapps.shoppingapp.data.model.Favorite
import com.skapps.shoppingapp.data.model.Product

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(product: Favorite)
    @Query("Select * from  favorite")
    fun getAllFavorites():List<Favorite>
    @Delete
    fun deleteFavorite(product:Favorite)
}