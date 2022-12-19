package com.skapps.shoppingapp.ui.commentDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skapps.shoppingapp.data.model.Comment
import com.skapps.shoppingapp.data.remote.ShoppingApi
import com.skapps.shoppingapp.data.remote.status.ApiStatus
import kotlinx.coroutines.launch

class CommentDetailsViewModel : ViewModel() {
    private var _commentList= MutableLiveData<List<Comment>>()
    val commentList : LiveData<List<Comment>>
        get() = _commentList
    private var _status=MutableLiveData<ApiStatus>()
    val status : LiveData<ApiStatus> get() = _status

    fun getAllCommentProduct(productId: Int) {
        _status.value=ApiStatus.LOADING
        viewModelScope.launch {
            try {
                _commentList.value = ShoppingApi.retrofitService.getAllCommentsProduct(productId)
                _status.value=ApiStatus.DONE
            } catch (e: Exception) {
                Log.e("Product Details ViewModel", "Gel All Comments Product $e")
                _status.value=ApiStatus.DONE
            }
        }
    }

}