package com.skapps.shoppingapp.data.responce

import com.google.gson.annotations.SerializedName

data class CustomerResponce( @SerializedName("name"        ) var name        : String? = null,
                             @SerializedName("surname"     ) var surname     : String? = null,
                             @SerializedName("email"       ) var email       : String? = null,
                             @SerializedName("phoneNumber" ) var phoneNumber : String? = null,
                             @SerializedName("budget"      ) var budget      : Int?    = null,
                             @SerializedName("status"      ) var status      : String? = "ACTIVE")