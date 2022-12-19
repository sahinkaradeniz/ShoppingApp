package com.skapps.shoppingapp.data.model

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("id"      ) var id      : Int?    = null,
    @SerializedName("header"  ) var userName : String? = null,
    @SerializedName("content" ) var content : String? = null,
    @SerializedName("rating"  ) var rating  : Int?    = null,
    @SerializedName("status"  ) var status  : String? = null
)