package com.skapps.shoppingapp.data.responce

import com.google.gson.annotations.SerializedName

data class PurchaseResponce(
    @SerializedName("id"              ) var id              : Int?                       = null,
    @SerializedName("customerId"      ) var customerId      : Int?                       = null,
    @SerializedName("orderedProducts" ) var orderedProducts : List<ProductResponce> = arrayListOf(),
    @SerializedName("totalPrice"      ) var totalPrice      : Int?                       = null,
    @SerializedName("status"          ) var status          : String?                    = null
)