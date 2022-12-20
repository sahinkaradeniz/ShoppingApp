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
    private val TAG = "History Purchase Viewmodel"
    init {
        getAllHistory()
    }
    fun getAllHistory() {
        viewModelScope.launch {
            try {
                _purchaseHistory.value = ShoppingApi.retrofitService.getAllPurchases(1)
           //     Log.e(TAG, "Get all history compalated ${_purchaseHistory.value.toString()}")
            } catch (e: Exception) {
                Log.e(TAG, " get all history exception ${e.toString()}")
            }
        }
    }
}