package com.skapps.shoppingapp.data.model

import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("id"           ) var id           : Int?                = null,
    @SerializedName("categoryName" ) var categoryName : String?             = null,
    @SerializedName("imageUuid"    ) var imageUuid    : String?             = null,
    @SerializedName("products"     ) var products     : List<Product> = arrayListOf(),
    @SerializedName("status"       ) var status       : String?             = null
)
