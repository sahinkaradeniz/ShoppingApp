package com.skapps.shoppingapp.data.remote

import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.remote.responce.ProductResponce
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://massive-moose-18.telebit.io"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ShoppingApiService{
    @GET("/v1/product")
    suspend fun getAllProduct(): ProductResponce
}
object ShoppingApi{
    val retrofitService:ShoppingApiService by lazy {retrofit.create(ShoppingApiService::class.java) }
}