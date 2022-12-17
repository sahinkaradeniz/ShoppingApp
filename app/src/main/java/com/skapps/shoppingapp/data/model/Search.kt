package com.skapps.shoppingapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "search")
data class Search(
    @ColumnInfo(name = "searchText")
    var search:String?=null):Serializable{
    @PrimaryKey(autoGenerate = true)
    var roomid=0
}