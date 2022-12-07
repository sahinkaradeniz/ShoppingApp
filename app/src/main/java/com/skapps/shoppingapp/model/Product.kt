package com.skapps.shoppingapp.model

import com.google.gson.annotations.SerializedName
data class Product (
    @SerializedName("id")
    var id: Int?= null,
    @SerializedName("brand")
    var brand : String? = null,
    @SerializedName("model")
    var model: String? = null,
    @SerializedName("shortDescription")
    var shortDescription : String? = null,
    @SerializedName("description" )
    var description : String? = null,
    @SerializedName("price")
    var price : Double? = null,
    @SerializedName("stockQuantity")
    var stockQuantity : Int?    = null,
    @SerializedName("averageRating" )
    var averageRating : Int?    = null,
    @SerializedName("imageUuid")
    var imageUuid : String? = null,
    @SerializedName("category")
    var category : String? = null,
    @SerializedName("status" )
    var status : String? = null
)