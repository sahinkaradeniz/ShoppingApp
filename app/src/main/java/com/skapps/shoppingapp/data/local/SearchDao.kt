package com.skapps.shoppingapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.skapps.shoppingapp.data.model.Search

@Dao
interface SearchDao {
    @Query("Select * from search")
    @Insert
    fun addSearchHistory(search: Search)
    @Delete
    fun deleteSearchHistory(search: Search)
}