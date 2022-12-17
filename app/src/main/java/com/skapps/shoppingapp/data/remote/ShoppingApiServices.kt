package com.skapps.shoppingapp.data.remote

import com.skapps.shoppingapp.data.model.Category
import com.skapps.shoppingapp.data.model.Customer
import com.skapps.shoppingapp.data.responce.CustomerResponce
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.model.Purchase
import com.skapps.shoppingapp.data.responce.PurchaseResponce
import retrofit2.http.*

interface ShoppingApiService{
    @GET("v1/product")
    suspend fun getAllProduct():List<Product>

    @GET("/v1/product/{productId}")
    suspend fun getProduct(@Path("productId") id:Int):Product

    @GET("/v1/category/{category}")
    suspend fun getCategoryProducts(@Path("category") id:Int):Category

    @GET("/v1/category")
    suspend fun getAllCategory():List<Category>

    @GET("/v1/customer/{customerId}")
    suspend fun getCustomer(@Path("customerId") id :Int):Customer

    @PUT("v1/customer/{customerId}")
    suspend fun updateCustomer(@Path("customerId") id:Int, @Body customer: CustomerResponce):Customer

    @POST("/v1/customer/1/purchase")
    suspend fun makepurchase(@Body purchase: Purchase)

    @GET("/v1/customer/{customerId}/purchase")
    suspend fun getAllPurchases(@Path("customerId") id:Int):PurchaseResponce
}
