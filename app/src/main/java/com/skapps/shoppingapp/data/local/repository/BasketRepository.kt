package com.skapps.shoppingapp.data.local.repository

import android.content.Context
import android.util.Log
import com.skapps.shoppingapp.data.local.database.BasketDatabase
import com.skapps.shoppingapp.data.model.Product

class BasketRepository{
    private lateinit var basketDatabase: BasketDatabase
    private var TAG="BasketLocalDatabase"

    fun  addBasket(product: Product,context: Context){
            try {
                val basketList=getAllBasket(context)
                basketDatabase= BasketDatabase.getBookDatabase(context)!!
                val prod=control(basketList,product)
                if (prod.stockQuantity!=0){
                    prod.stockQuantity=prod.stockQuantity?.plus(1)
                    prod.price=prod.price?.plus(product.price!!)
                    basketDatabase.getBasketDao().updateProductBasket(prod)
                }else{
                    basketDatabase.getBasketDao().insertProduct(product)
                }
            }catch (e:Exception){
                Log.e(TAG,e.message.toString())
            }

    }

    fun deleteAllBasket(context: Context){
        try {
            basketDatabase= BasketDatabase.getBookDatabase(context)!!
            basketDatabase.getBasketDao().deleteAllBasket()
        }catch (e :Exception){
            Log.e(TAG,e.message.toString())
        }
    }
    fun deleteProductBasket(product: Product,context: Context){
            try {
                basketDatabase= BasketDatabase.getBookDatabase(context)!!
                basketDatabase.getBasketDao().deleteProductBasket(product)
            }catch (e :Exception){
                Log.e(TAG,e.message.toString())
            }
    }

    fun minusProductBasket(product: Product,context:Context){
            basketDatabase= BasketDatabase.getBookDatabase(context)!!
            try {
                if (product.stockQuantity==1){
                    basketDatabase.getBasketDao().deleteProductBasket(product)
                }else{
                    val productPrice= product.price?.div(product.stockQuantity!!)
                     product.price=product.price?.minus(productPrice!!)
                    product.stockQuantity=product.stockQuantity!!.minus(1)
                    basketDatabase.getBasketDao().updateProductBasket(product)
                    getAllBasket(context)
                }
            }catch (e : Exception){
                Log.e(TAG,e.message.toString())
            }

    }

    private  fun control(list: List<Product>,product: Product):Product{
        var prod=Product(1,"0","0","0","0",0.0,0,0.0,"0","0","0")
        for (i in list){
            if (i.id==product.id){
                prod=i
                break
            }
        }
        return prod
    }

    fun getAllBasket(context: Context):List<Product>{
            basketDatabase= BasketDatabase.getBookDatabase(context)!!
            return basketDatabase.getBasketDao().getBasket()
    }

    fun getBasketTotalPrice(basketList:List<Product>):Double{
        var totalPrice =0.0
        for (i in basketList){
            totalPrice= i.price?.let { totalPrice.plus(it)}!!
        }
        return  totalPrice
    }

    fun getBasketProductSize(basketList:List<Product>):Int{
        var totalSize=0
        for (i in basketList){
            totalSize= i.stockQuantity?.let { totalSize.plus(it)}!!
        }
        return totalSize
    }

}