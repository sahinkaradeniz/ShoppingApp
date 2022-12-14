package com.skapps.shoppingapp.ui.productdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.model.Comment
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.remote.ApiStatus
import com.skapps.shoppingapp.data.remote.ShoppingApi
import kotlinx.coroutines.launch

class ProductDetailsViewModel: ViewModel() {
   private val TAG="ProductDetailsViewModel"
   private var _detailsProduct=MutableLiveData<Product>()
   val detailsProduct:LiveData<Product>
   get() = _detailsProduct
   private var _commentList=MutableLiveData<ArrayList<Comment>>()
   val commentList : LiveData<ArrayList<Comment>>
   get() = _commentList
   private var _status=MutableLiveData<ApiStatus>()
   val  status:LiveData<ApiStatus>
   get() = _status

   var productId:Int?=null

   fun getProduct(productId:Int){
      viewModelScope.launch {
          _status.value=ApiStatus.LOADING
          try {
              _status.value=ApiStatus.DONE
              _detailsProduct.value= ShoppingApi.retrofitService.getProduct(productId)
          }catch (e : Exception){
              _status.value=ApiStatus.ERROR
              Log.e(TAG,"Api eror ${e.message.toString()}")
          }
      }

   }

}