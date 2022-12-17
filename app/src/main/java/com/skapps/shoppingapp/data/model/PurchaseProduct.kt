package com.skapps.shoppingapp.data.model

import com.google.gson.annotations.SerializedName

data class PurchaseProduct(
    @SerializedName("productId" ) var productId : Int?    = null,
    @SerializedName("quantity"  ) var quantity  : Int?    = null,
    @SerializedName("unitPrice" ) var unitPrice : Int?    = null,
    @SerializedName("status"    ) var status    : String? = null
)
