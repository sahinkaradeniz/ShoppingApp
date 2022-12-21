package com.skapps.shoppingapp.ui.purchase

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.local.repository.BasketRepository
import com.skapps.shoppingapp.data.model.Customer
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.model.Purchase
import com.skapps.shoppingapp.data.model.PurchaseProduct
import com.skapps.shoppingapp.data.remote.status.ApiStatus
import com.skapps.shoppingapp.data.remote.status.PurchaseStatus
import com.skapps.shoppingapp.data.remote.ShoppingApi
import com.skapps.shoppingapp.data.responce.CustomerResponce
import kotlinx.coroutines.launch

class PurchaseViewModel : ViewModel() {
    private var _apiStatus=MutableLiveData<ApiStatus>()
    val apiStatus:LiveData<ApiStatus> get() = _apiStatus
    private var _user=MutableLiveData<Customer>()
    val user:LiveData<Customer> get() = _user
    private var _productsList =MutableLiveData<List<Product>>()
    val productsList:LiveData<List<Product>> get() = _productsList
    private var _totalPrice=MutableLiveData<Double>()
    val totalPrice:LiveData<Double> get() = _totalPrice
    private var _productQuantity=MutableLiveData<Int>()
    val productQuantity:LiveData<Int> get() = _productQuantity
    private var _purchaseStatus= MutableLiveData<PurchaseStatus>()
    val purchaseStatus:LiveData<PurchaseStatus> get() = _purchaseStatus
    private var _walletControl=MutableLiveData<Boolean>()
    val walletControl : LiveData<Boolean> get() = _walletControl
    private var price:Int=0
    private val basketRepository= BasketRepository()
    private val TAG="Purchase View Model"
    private val purchases=Purchase(1, listOf(),"ACTIVE")

    private fun getUser(userid:Int){
        _apiStatus.value= ApiStatus.LOADING
        viewModelScope.launch {
            try {
                _user.value=ShoppingApi.retrofitService.getCustomer(userid)
                purchases.customerId=_user.value!!.id
                purchases.status=_user.value!!.status
                _apiStatus.value= ApiStatus.DONE
            }catch (e:Exception){
                _apiStatus.value= ApiStatus.ERROR
                Log.e(TAG, "get user : $this $e")
            }
        }
    }
   fun getAllBasketList(context: Context){
        viewModelScope.launch {
            _productsList.value=basketRepository.getAllBasket(context)
            getTotalPrice()
            getProductQuantity()
            getUser(1)
            basketToPurchase()
        }
    }
    private fun getTotalPrice(){
        viewModelScope.launch {
            _totalPrice.value= _productsList.value?.let { basketRepository.getBasketTotalPrice(it) }
        }
    }


    private fun getProductQuantity(){
        viewModelScope.launch {
            _productQuantity.value= _productsList.value?.let {
                basketRepository.getBasketProductSize(it)
            }
        }
    }

    private fun basketToPurchase(){
        if (_productsList.value!!.isNotEmpty()){
            val purchaseProduct=ArrayList<PurchaseProduct>()
            for ( product in _productsList.value!!){
               val unitprice= product.price?.div(product.stockQuantity!!)
                val purchase=PurchaseProduct(product.id,product.stockQuantity,unitprice?.toInt(),product.status)
                purchaseProduct.add(purchase)
            }
            purchases.orderedProducts=purchaseProduct
        }
    }
     fun deleteBasket(context: Context){
        viewModelScope.launch {
            try {
                basketRepository.deleteAllBasket(context)
                getAllBasketList(context)
            }catch (e:Exception){
                Log.e(TAG,"delete basket "+e.message.toString())
            }
        }
    }

     fun purchaseProducts(context: Context){
        viewModelScope.launch {
            _walletControl.value= _totalPrice.value?.let { walletControl(it.toInt())}
            if (_walletControl.value!!){
                _purchaseStatus.value= PurchaseStatus.LOADING
                try {
                    val  p =basketRepository.getTotalPrice(context)
                    ShoppingApi.retrofitService.makepurchase(purchases)
                    deleteMoneyCustomer(p.toInt())
                    _purchaseStatus.value= PurchaseStatus.DONE

                }catch (e:Exception){
                    _purchaseStatus.value= PurchaseStatus.ERROR
                    Log.e(TAG, "purchase product : $e")
                }
            }
        }
    }
   private fun walletControl(price:Int):Boolean{
       val bugdet =_user.value?.budget!!
       return bugdet>= price
    }
    fun deleteMoneyCustomer(money:Int){
        viewModelScope.launch {
            try {
                val customer=_user.value!!
                var customerBudget = customer.budget
                customerBudget= customerBudget?.minus(money)
                val c= CustomerResponce(customer.name,customer.surname,customer.email,customer.phoneNumber,customerBudget)
                _user.value=ShoppingApi.retrofitService.updateCustomer(1,c)
                getUser(customer.id!!)
            }catch (e:Exception){
                Log.e(TAG,e.message.toString())
            }
        }
    }
}