package com.skapps.shoppingapp.data.model

import com.google.gson.annotations.SerializedName

data class Purchase(
               @SerializedName("customerId"      ) var customerId      : Int?                       = null,
               @SerializedName("orderedProducts" ) var orderedProducts : List<PurchaseProduct> = arrayListOf(),
               @SerializedName("status"          ) var status          : String?                    = null)