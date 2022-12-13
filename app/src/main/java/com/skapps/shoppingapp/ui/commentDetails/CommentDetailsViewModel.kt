package com.skapps.shoppingapp.ui.commentDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skapps.shoppingapp.data.model.Comment

class CommentDetailsViewModel : ViewModel() {
    private var _commentList= MutableLiveData<ArrayList<Comment>>()
    val commentList : LiveData<ArrayList<Comment>>
        get() = _commentList
    init {
        getCommentList()
    }
    private fun getCommentList(){
        val comment= Comment("2","21312","Sahin Karadeniz","Ürün krık geldi iade edeceğim","4.7")
        val commentLis=ArrayList<Comment>()
        commentLis.add(comment)
        commentLis.add(comment)
        commentLis.add(comment)
        commentLis.add(comment)
        commentLis.add(comment)
        commentLis.add(comment)
        commentLis.add(comment)
        _commentList.value=commentLis
    }
}