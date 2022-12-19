package com.skapps.shoppingapp.ui.purchaseHistory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.remote.ShoppingApi
import com.skapps.shoppingapp.data.responce.PurchaseResponce
import com.skapps.shoppingapp.data.model.PurchaseAdapterList
import kotlinx.coroutines.launch

class HistoryPurchaseViewModel : ViewModel() {
    private var _purchaseHistory = MutableLiveData<List<PurchaseResponce>>()
    val purchaseHistory: LiveData<List<PurchaseResponce>> get() = _purchaseHistory
    private var _productList = MutableLiveData<List<PurchaseAdapterList>>()
     val productList: LiveData<List<PurchaseAdapterList>> get() = _productList
    private val TAG = "History Purchase Viewmodel"

    init {
        getAllHistory()
    }

    fun getAllHistory() {
        viewModelScope.launch {
            try {
                _purchaseHistory.value = ShoppingApi.retrofitService.getAllPurchases(1)
                Log.e(TAG, "Get all history compalated ${_purchaseHistory.value.toString()}")
             //   getAllHistoryProduct()
            } catch (e: Exception) {
                Log.e(TAG, " get all history exception ${e.toString()}")
            }
        }
    }

   fun Product.compare(productResponce:List<PurchaseResponce>){
       var product=Product(1,"","","","",0.0,0,0.0,"","","")
        viewModelScope.launch {
            try {
                val count=0
                for (i in productResponce){
                  val temp=  i.orderedProducts.get(count)
                    product=ShoppingApi.retrofitService.getProduct(temp.productId!!)
                    _productList.value?.get(count)!!.id=i.id
                }
           //     product = ShoppingApi.retrofitService.getProduct(productResponce.)
            }catch (e:Exception){
                Log.e(TAG,"compare to ${e.toString()}")
            }
        }
    }

/*    fun getAllHistoryProduct(){
        val purchaseAdapterList = PurchaseAdapterList(1, 1, arrayListOf(), 1, "Ac")
        val productList = ArrayList<Product>()
        viewModelScope.launch {
            for (product in _purchaseHistory.value!!) {
                val prod = product.id?.let { ShoppingApi.retrofitService.getProduct(it) }
                prod?.stockQuantity=product.quantity
                prod?.let { productList.add(it) }
            }
            purchaseAdapterList.id = _productList.value!!.id
            purchaseAdapterList.customerId = _productList.value!!.customerId
            purchaseAdapterList.totalPrice = _productList.value!!.totalPrice
            _productList.value=purchaseAdapterList
        }
    }*/
}