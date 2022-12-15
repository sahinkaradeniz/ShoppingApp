package com.skapps.shoppingapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skapps.shoppingapp.data.model.Customer

class ProfileViewModel : ViewModel() {
    private val _customer=MutableLiveData<Customer>()
    val customer:LiveData<Customer> get() = _customer
    init {
        getCustomer()
    }
    fun getCustomer(){

    }
}