package com.skapps.shoppingapp.utils

class API {
    companion object{
        var api = "0"

        @JvmName("getApi1")
        fun  getApi(): String{
            return api
        }

        @JvmName("setApi1")
        fun setApi (apitext:String){
            api =apitext
        }
    }
}
