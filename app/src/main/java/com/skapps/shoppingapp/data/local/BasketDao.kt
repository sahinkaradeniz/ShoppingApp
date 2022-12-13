package com.skapps.shoppingapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.skapps.shoppingapp.data.model.Product

@Dao
interface BasketDao {
    @Insert
    suspend fun insertProduct(product: Product)

    @Query("Select * from basketdatabase")
    suspend fun getBasket():List<Product>

    @Delete
    suspend fun deleteProductBasket(product: Product)

    @Query("Delete from basketdatabase")
    suspend fun deleteAllBasket()
}