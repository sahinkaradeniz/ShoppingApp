package com.skapps.shoppingapp.ui.home

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val test = MutableLiveData<String>()
    val countDownTimer= object:CountDownTimer(100000L,300L){
        override fun onTick(millisUntilFinished: Long) {
            test.value=millisUntilFinished.toString()
        }

        override fun onFinish() {
            TODO("Not yet implemented")
        }

    }
    init {
        countDownTimer.start()
    }


}
