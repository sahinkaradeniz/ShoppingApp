package com.skapps.shoppingapp.data.remote

import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.responce.ProductResponce
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface ShoppingApiService{
    @GET("v1/product")
    suspend fun getAllProduct(): ProductResponce

    @GET("/v1/product/1")
    suspend fun getProduct():Product
}
