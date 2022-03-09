package cn.wenhe9.order.logic.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *@author DuJinliang
 *2021/11/18
 */
object ServiceCreator {
    private const val BASE_URL = "http://192.168.43.129:8080/onlineShop/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> create(serviceClass : Class<T>) : T = retrofit.create(serviceClass)

    inline fun <reified T> create() : T  = create(T::class.java)
}