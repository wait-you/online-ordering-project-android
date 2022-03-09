package cn.wenhe9.order.logic

import androidx.lifecycle.liveData
import cn.wenhe9.order.logic.network.OrderNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 *@author DuJinliang
 *2021/11/18
 */
object Repository {

    fun getShopList() = fire(Dispatchers.IO){
        val shopList = OrderNetwork.getShopList()

        if (shopList.isNotEmpty()){
            Result.success(shopList)
        }else{
            Result.failure(RuntimeException("response data size is 0"))
        }
    }

    fun getFoodList(shopId : Int) = fire(Dispatchers.IO) {
        val foodList = OrderNetwork.getFoodList(shopId)

        if (foodList.size > 0){
            Result.success(foodList)
        }else{
            Result.failure(RuntimeException("response data size is 0"))
        }
    }

    private fun <T> fire(context : CoroutineContext, block : suspend () -> Result<T>) = liveData<Result<T>>(context) {
        val result = try {
            block()
        }catch (e : Exception){
            Result.failure<T>(e)
        }

        emit(result)
    }
}