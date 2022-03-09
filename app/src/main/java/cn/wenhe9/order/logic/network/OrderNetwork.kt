package cn.wenhe9.order.logic.network

import cn.wenhe9.order.logic.network.OrderNetwork.await
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *@author DuJinliang
 *2021/11/18
 */
object OrderNetwork {
    private val onlineShopService = ServiceCreator.create<OnlineShopService>()

    suspend fun getShopList() = onlineShopService.shopList().await()

    suspend fun getFoodList(shopId : Int) = onlineShopService.foodListByShopId(shopId).await()

    private suspend fun <T> Call<T>.await() : T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()

                    if (body != null){
                        continuation.resume(body)
                    }else{
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }

    }
}