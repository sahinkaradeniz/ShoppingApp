package com.skapps.shoppingapp.data.local

import android.content.Context
import android.util.Log
import com.skapps.shoppingapp.data.model.Product
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class BasketLocalDatabase{
    private lateinit var basketDatabase: BasketDatabase
    private var TAG="BasketLocalDatabase"
    fun  addBasket(product: Product,context: Context){
         GlobalScope.launch {
            try {
                val basketList=getAllBasket(context)
                basketDatabase= BasketDatabase.getBookDatabase(context)!!
                val prod=control(basketList,product)
                if (prod.stockQuantity!=0){
                    prod.stockQuantity=prod.stockQuantity?.plus(1)
                    basketDatabase.getBasketDao().updateProductBasket(prod)
                }else{
                    basketDatabase.getBasketDao().insertProduct(product)
                }
            }catch (e:Exception){
                Log.e(TAG,e.message.toString())
            }
        }
    }
  private  fun control(list: List<Product>,product: Product):Product{
        var prod=Product(1,"0","0","0","0",0.0,0,0,"0","0","0")
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

}