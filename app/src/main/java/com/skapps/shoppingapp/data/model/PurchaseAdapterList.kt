package com.skapps.shoppingapp.data.model

import com.google.gson.annotations.SerializedName
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.responce.ProductResponce

data class PurchaseAdapterList(
    @SerializedName("id"              ) var id              : Int?                       = null,
    @SerializedName("customerId"      ) var customerId      : Int?                       = null,
    @SerializedName("orderedProducts" ) var orderedProducts : List<Product> = arrayListOf(),
    @SerializedName("totalPrice"      ) var totalPrice      : Int?                       = null,
    @SerializedName("status"          ) var status          : String?                    = null
)