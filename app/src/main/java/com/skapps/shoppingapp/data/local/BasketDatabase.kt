package com.skapps.shoppingapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skapps.shoppingapp.data.model.Basket
import com.skapps.shoppingapp.data.model.Product

@Database(entities = [Product::class], version = 1)
abstract class BasketDatabase: RoomDatabase() {
    abstract fun getBasketDao(): BasketDao
    companion object{
        private var instance: BasketDatabase? = null
        fun getBookDatabase(context: Context): BasketDatabase?{
            if (instance == null){
                instance = Room.databaseBuilder(context,
                    BasketDatabase::class.java,"basket.db").allowMainThreadQueries().build()
            }
            return instance
        }
    }
}