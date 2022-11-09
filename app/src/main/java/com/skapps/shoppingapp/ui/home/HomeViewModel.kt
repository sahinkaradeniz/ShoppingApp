package com.skapps.shoppingapp.ui.home

import android.os.CountDownTimer
import android.provider.Settings.Global.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.model.Product
import com.skapps.shoppingapp.model.ProductModel

class HomeViewModel : ViewModel() {
    private var _productList=MutableLiveData<ArrayList<ProductModel>>()
    val products:LiveData<ArrayList<ProductModel>> get() = _productList
    private val product= Product("Test deneme 2 2 342 hoppa","Harley Davidson","299.99",4.6)
    private fun getAllProductList(){
        val productList=ArrayList<Product>()
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        val productModel = ProductModel(2,"Ayakkabılar",productList)
        val productModelList=ArrayList<ProductModel>()
        productModelList.add(productModel)
        productModelList.add(productModel)
        productModelList.add(productModel)
        productModelList.add(productModel)
        _productList.value=productModelList
    }
    init {
        getAllProductList()
    }



}
