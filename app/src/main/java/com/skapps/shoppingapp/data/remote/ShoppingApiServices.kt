package com.skapps.shoppingapp.data.remote

import com.skapps.shoppingapp.data.model.Category
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.responce.ProductResponce
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ShoppingApiService{
    @GET("v1/product")
    suspend fun getAllProduct():List<Product>

    @GET("/v1/product/1")
    suspend fun getProduct():Product

    @GET("/v1/category/{category}")
    suspend fun getCategoryProducts(
        @Path("category") id:Int
    ):Category
}
