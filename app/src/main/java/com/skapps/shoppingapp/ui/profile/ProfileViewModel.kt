package com.skapps.shoppingapp.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.model.Customer
import com.skapps.shoppingapp.data.remote.ShoppingApi
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val TAG="ProfileViewModel"
    private val _customer=MutableLiveData<Customer>()
    val customer:LiveData<Customer> get() = _customer
    init {
        getCustomer(1)
    }
    private fun getCustomer(id:Int){
        viewModelScope.launch {
            try {
                _customer.value=ShoppingApi.retrofitService.getCustomer(id)
            }catch(e:Exception){
                Log.e(TAG,e.message.toString())
            }
        }
    }
}