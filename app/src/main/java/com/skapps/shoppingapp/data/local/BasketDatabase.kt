package com.skapps.shoppingapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skapps.shoppingapp.data.model.Product

@Database(entities = arrayOf(Product::class), version = 1)
abstract class BasketDatabase: RoomDatabase() {
    abstract fun basketDao():BasketDao
    companion object{
        @Volatile private var instance:BasketDatabase?=null
        private val lock =Any()
        operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?:makeDatabase(context).also{
                instance=it
            }
        }
        private fun makeDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,BasketDatabase::class.java,"basketdatabase"
        ).build()
    }
}

/**
 * @Database(entities = arrayOf(Country::class),version = 1)
abstract class CountryDatabase : RoomDatabase() {

abstract fun countryDao() : CountryDao

//Singleton

companion object {

@Volatile private var instance : CountryDatabase? = null

private val lock = Any()

operator fun invoke(context : Context) = instance ?: synchronized(lock) {
instance ?: makeDatabase(context).also {
instance = it
}
}


private fun makeDatabase(context : Context) = Room.databaseBuilder(
context.applicationContext,CountryDatabase::class.java,"countrydatabase"
).build()
}
}
 */