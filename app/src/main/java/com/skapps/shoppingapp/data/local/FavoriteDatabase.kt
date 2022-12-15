package com.skapps.shoppingapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skapps.shoppingapp.data.model.Product


@Database(entities = [Product::class], version = 1)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun getFavoriteDao(): FavoritesDao
    companion object{
        private var instance: FavoriteDatabase? = null
        fun getFavoriteDatabase(context: Context): FavoriteDatabase?{
            if (instance == null){
                instance = Room.databaseBuilder(context,
                    FavoriteDatabase::class.java,"favorites.db").allowMainThreadQueries().build()
            }
            return instance
        }
    }
}