package cn.wenhe9.order.logic.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 *@author DuJinliang
 *2021/11/20
 */
@Parcelize
data class CarBean(val foodId : Int, val foodName : String, var foodCount : Int, var foodPrice : Int, val foodPic : String) : Parcelable
