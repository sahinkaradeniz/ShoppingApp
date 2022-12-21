package com.skapps.shoppingapp.ui.addIPCode

import android.content.Context
import androidx.lifecycle.ViewModel
import com.skapps.shoppingapp.utils.API

class AddAPIViewModel : ViewModel() {

    fun addIPAdress( ip: String){
        API.setApi(ip)
    }
}