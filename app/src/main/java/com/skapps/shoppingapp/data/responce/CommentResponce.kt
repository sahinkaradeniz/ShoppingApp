package com.skapps.shoppingapp.data.responce

import com.google.gson.annotations.SerializedName

data class CommentResponce(
    @SerializedName("header"  ) var header  : String? = null,
    @SerializedName("content" ) var content : String? = null,
    @SerializedName("rating"  ) var rating  : Double?    = null,
    @SerializedName("status"  ) var status  : String? = null
)