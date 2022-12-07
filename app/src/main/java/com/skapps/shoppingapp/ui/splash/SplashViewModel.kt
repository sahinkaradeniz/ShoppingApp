package com.skapps.shoppingapp.ui.splash

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SplashViewModel : ViewModel() {
    private val _timerStatus=MutableLiveData<Boolean>()
    val timer:LiveData<Boolean> get() = _timerStatus

    init {
        Starttimer()
    }
    private fun Starttimer() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
               _timerStatus.value=true
            }
        }.start()
    }
}