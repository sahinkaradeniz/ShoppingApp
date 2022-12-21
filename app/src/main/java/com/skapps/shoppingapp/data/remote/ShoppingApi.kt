package com.skapps.shoppingapp.data.remote

import com.skapps.shoppingapp.utils.API
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val api=API.getApi()
private var BASE_URL = "http://$api:8080"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object ShoppingApi{
    val retrofitService:ShoppingApiService by lazy {retrofit.create(ShoppingApiService::class.java) }
}
