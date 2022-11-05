package com.skapps.shoppingapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(var name:String,var brand:String,var price:String, var stars: Double):Serializable{
}