package com.skapps.shoppingapp.data.remote

import com.skapps.shoppingapp.data.model.*
import com.skapps.shoppingapp.data.responce.CustomerResponce
import com.skapps.shoppingapp.data.responce.CommentResponce
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
    suspend fun getAllPurchases(@Path("customerId") id:Int):List<PurchaseResponce>

    @POST("/v1/customer/1/comment/{productId}")
    suspend fun makeCommentProduct(@Path("productId") id :Int,@Body comment:CommentResponce)

    @GET("/v1/comment/product/{productId}")
    suspend fun getAllCommentsProduct(@Path("productId") id:Int):List<Comment>

    @GET("/v1/product/search/{searchText}")
    suspend fun elasticSearchProduct(@Path("searchText") text:String):List<Product>
}
