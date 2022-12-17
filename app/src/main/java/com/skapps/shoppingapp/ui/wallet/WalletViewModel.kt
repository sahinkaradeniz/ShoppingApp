package com.skapps.shoppingapp.ui.wallet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.model.Customer
import com.skapps.shoppingapp.data.responce.CustomerResponce
import com.skapps.shoppingapp.data.remote.ShoppingApi
import kotlinx.coroutines.launch

class WalletViewModel : ViewModel() {
    private var _customer=MutableLiveData<Customer>()
    val customer : LiveData<Customer> get() = _customer
    private val TAG="Wallet View Model"
    private val _moneyStatus=MutableLiveData<Boolean>()
    val moneyStatus:LiveData<Boolean> get() = _moneyStatus

    init {
        getCustomer(1)
        _moneyStatus.value=false
    }
    private fun getCustomer(id:Int){
        viewModelScope.launch {
            try {
                _customer.value= ShoppingApi.retrofitService.getCustomer(id)
            }catch(e:Exception){
                Log.e(TAG,e.message.toString())
            }
        }
    }
    fun addedMoneyCustomer(money:Int){
        viewModelScope.launch {
            try {
                val customer=_customer.value!!
                customer.budget=customer.budget?.plus(money)
                val c= CustomerResponce(customer.name,customer.surname,customer.email,customer.phoneNumber,customer.budget)
                _customer.value=ShoppingApi.retrofitService.updateCustomer(customer.id!!,c)
                getCustomer(customer.id!!)
                _moneyStatus.value=true
            }catch (e:Exception){
                Log.e(TAG,e.message.toString())
            }
        }
    }
}