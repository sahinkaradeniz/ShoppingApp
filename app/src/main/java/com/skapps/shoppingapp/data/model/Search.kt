package com.skapps.shoppingapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "search")
data class Search(
    @ColumnInfo(name = "searchText")
    var search:String?=null)