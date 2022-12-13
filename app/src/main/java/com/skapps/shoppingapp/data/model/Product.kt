package com.skapps.shoppingapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "basketdatabase")
data class Product (
    @ColumnInfo(name="id")
    @SerializedName("id")
    var id: Int?= null,
    @ColumnInfo(name="brand")
    @SerializedName("brand")
    var brand : String? = null,
    @ColumnInfo(name = "model")
    @SerializedName("model")
    var model: String? = null,
    @ColumnInfo(name = "shortDescription")
    @SerializedName("shortDescription")
    var shortDescription : String? = null,
    @ColumnInfo(name="description")
    @SerializedName("description" )
    var description : String? = null,
    @SerializedName("price")
    @ColumnInfo(name="price")
    var price : Double? = null,
    @ColumnInfo(name="stockQuantity")
    @SerializedName("stockQuantity")
    var stockQuantity : Int?    = null,
    @ColumnInfo(name="averageRating")
    @SerializedName("averageRating" )
    var averageRating : Int?    = null,
    @ColumnInfo(name="imageUuid")
    @SerializedName("imageUuid")
    var imageUuid : String? = null,
    @ColumnInfo(name="category")
    @SerializedName("category")
    var category : String? = null,
    @ColumnInfo(name = "status")
    @SerializedName("status" )
    var status : String? = null
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var roomid=0
}