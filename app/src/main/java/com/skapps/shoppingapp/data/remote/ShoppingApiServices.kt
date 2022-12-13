package com.skapps.shoppingapp.data.remote

import com.skapps.shoppingapp.data.model.Category
import com.skapps.shoppingapp.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ShoppingApiService{
    @GET("v1/product")
    suspend fun getAllProduct():List<Product>

    @GET("/v1/product/{productId}")
    suspend fun getProduct(@Path("productId") id:Int):Product

    @GET("/v1/category/{category}")
    suspend fun getCategoryProducts(@Path("category") id:Int):Category
    @GET("/v1/category")
    suspend fun getAllCategory():List<Category>
}
