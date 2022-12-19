package com.skapps.shoppingapp.ui.commentToProduct

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.model.Customer
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.remote.ShoppingApi
import com.skapps.shoppingapp.data.remote.status.ApiStatus
import com.skapps.shoppingapp.data.responce.CommentResponce
import kotlinx.coroutines.launch

class CommentProductViewModel : ViewModel() {
    private var _product=MutableLiveData<Product>()
    val product:LiveData<Product> get() = _product
    private var _user=MutableLiveData<Customer>()
    val user:LiveData<Customer> get() = _user
    private val TAG="Comment Product ViewModel"
    private val _status=MutableLiveData<ApiStatus>()
    val status:LiveData<ApiStatus> get() = _status
    init {
        getUser(1)
    }

    private fun getUser(id: Int) {
        viewModelScope.launch {
            try {
                _user.value=ShoppingApi.retrofitService.getCustomer(id)
            }catch (e:Exception){
                Log.e(TAG, "get user func : $e")

            }
        }
    }

   fun addComment(rating:Double,text:String){
        viewModelScope.launch {
            _status.value=ApiStatus.LOADING
            try {
                val comment=CommentResponce(user.value!!.name,text,rating,"ACTIVE")
                _product.value?.id?.let { ShoppingApi.retrofitService.makeCommentProduct(it,comment)
                    _status.value=ApiStatus.DONE
                }
            }catch (e:Exception){
                Log.e(TAG, "add comment func : $e")
                _status.value=ApiStatus.ERROR
            }
        }
    }

    fun getProduct(id:Int){
        viewModelScope.launch {

            try {
                _product.value=ShoppingApi.retrofitService.getProduct(id)

            }catch (e:Exception){
                Log.e(TAG, "get product func: $e")

            }
        }
    }


}