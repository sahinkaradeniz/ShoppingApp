package com.skapps.shoppingapp.data.responce

import com.google.gson.annotations.SerializedName

data class ProductProductResponce(
    @SerializedName("id"        ) var id        : Int?    = null,
    @SerializedName("productId" ) var productId : Int?    = null,
    @SerializedName("quantity"  ) var quantity  : Int?    = null,
    @SerializedName("unitPrice" ) var unitPrice : Int?    = null,
    @SerializedName("status"    ) var status    : String? = null
)