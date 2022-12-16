package com.skapps.shoppingapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.model.Search

@Database(entities = [Search::class], version = 1)
abstract class SearchDatabase: RoomDatabase() {
    abstract fun getSearchDao(): SearchDao
    companion object{
        private var instance: SearchDatabase? = null
        fun getSearchDatabase(context: Context): SearchDatabase?{
            if (instance == null){
                instance = Room.databaseBuilder(context,
                    SearchDatabase::class.java,"search.db").allowMainThreadQueries().build()
            }
            return instance
        }
    }
}