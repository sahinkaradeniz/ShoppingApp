package com.skapps.shoppingapp.ui.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.model.Category
import com.skapps.shoppingapp.data.remote.ApiStatus
import com.skapps.shoppingapp.data.remote.ShoppingApi
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val TAG = "CategoryViewModel"
   private var _categoryList=MutableLiveData<List<Category>>()
    val categoryList:LiveData<List<Category>>
    get() = _categoryList

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        getAllCategory()
    }

    private fun getAllCategory(){
        viewModelScope.launch {
          _status.value=ApiStatus.LOADING
            try {
                _status.value=ApiStatus.DONE
                _categoryList.value= ShoppingApi.retrofitService.getAllCategory()
                Log.e(TAG,categoryList.toString())
            }catch (e : Exception){
                _status.value=ApiStatus.ERROR
                Log.e(TAG,e.message.toString())
            }
        }
    }
}