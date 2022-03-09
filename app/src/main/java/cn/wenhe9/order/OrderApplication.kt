package cn.wenhe9.order

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 *@author DuJinliang
 *2021/11/18
 */
class OrderApplication : Application(){
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}