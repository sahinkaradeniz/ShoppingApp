package com.skapps.shoppingapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "basket")
data class Basket(
    @ColumnInfo(name="uid")
    var id:String,
    @ColumnInfo(name="product")
    var product: Product):Serializable{
    @PrimaryKey(autoGenerate = true)
    var roomid=0
}