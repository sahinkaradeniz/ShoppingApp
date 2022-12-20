package com.skapps.shoppingapp.data.local.repository

import android.content.Context
import android.util.Log
import com.skapps.shoppingapp.data.local.database.SearchDatabase
import com.skapps.shoppingapp.data.model.Search

class SearchRepository {
    private val TAG="Search Local Database"
    private lateinit var searchDatabase: SearchDatabase

    fun getAllSearchHistory(context: Context):List<Search>{
        try {
            searchDatabase= SearchDatabase.getSearchDatabase(context)!!
            return searchDatabase.getSearchDao().getAllSearchHistory()
        }catch (e:Exception){
            Log.e(TAG,e.message.toString())
            return arrayListOf()
        }
    }
    fun deleteSearch(search: Search,context: Context){
        try {
           searchDatabase= SearchDatabase.getSearchDatabase(context)!!
           searchDatabase.getSearchDao().deleteSearchHistory(search)
        }catch (e:Exception){
           Log.e(TAG,e.message.toString())
        }
    }

    fun addSearch(search: Search,context: Context){
        try {
            searchDatabase= SearchDatabase.getSearchDatabase(context)!!
            searchDatabase.getSearchDao().addSearchHistory(search)
        }catch (e:Exception){
            Log.e(TAG,e.message.toString())
        }
    }

}