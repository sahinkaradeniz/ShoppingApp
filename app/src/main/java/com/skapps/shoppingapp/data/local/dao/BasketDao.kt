package com.skapps.shoppingapp.data.local.dao

import androidx.room.*
import com.skapps.shoppingapp.data.model.Product

@Dao
interface BasketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertProduct(product: Product)

    @Query("Select * from basketdatabase")
    fun getBasket():List<Product>

    @Delete
     fun deleteProductBasket(product: Product)

    @Query("Delete from basketdatabase")
     fun deleteAllBasket()

     @Update
     fun updateProductBasket(product: Product)
}