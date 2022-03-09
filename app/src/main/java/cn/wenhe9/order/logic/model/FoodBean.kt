package cn.wenhe9.order.logic.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 *@author DuJinliang
 *2021/11/18
 */
@Parcelize
data class FoodBean(val id : Int, val foodName : String, val taste : String, val saleNum : String, val price : Int, val count : Int, val foodPic : String, val shopId : Int) : Parcelable