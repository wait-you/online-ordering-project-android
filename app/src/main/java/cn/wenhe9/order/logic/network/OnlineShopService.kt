package cn.wenhe9.order.logic.network

import cn.wenhe9.order.logic.model.FoodBean
import cn.wenhe9.order.logic.model.ShopBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *@author DuJinliang
 *2021/11/18
 */
interface OnlineShopService {
    @GET("shopList")
    fun shopList() : Call<List<ShopBean>>

    @GET("foodList/{shopId}")
    fun foodListByShopId(@Path("shopId") shopId : Int) : Call<List<FoodBean>>
}