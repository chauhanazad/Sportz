package com.example.sportzinteractive

import android.app.Application
import android.content.Context

class MyApplication: Application() {

    companion object {
        private var instance: MyApplication? =null
        fun getInstance() = instance?: MyApplication()
        var mContext: Context? = null
        fun getContext() = mContext
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        mContext = applicationContext
    }
}