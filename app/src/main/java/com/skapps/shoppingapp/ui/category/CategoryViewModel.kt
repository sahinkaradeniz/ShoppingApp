package com.skapps.shoppingapp.ui.category

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skapps.shoppingapp.model.Category

class CategoryViewModel : ViewModel() {
    var categoryList=MutableLiveData<ArrayList<Category>>()
fun getList(){
    val category=Category("1","Outdoor Ayakkabı","null")
    val clist=ArrayList<Category>()
    clist.add(category)
    clist.add(category)
    clist.add(category)
    clist.add(category)
    clist.add(category)
    clist.add(category)
    clist.add(category)
    clist.add(category)
    categoryList.value=clist
}
}