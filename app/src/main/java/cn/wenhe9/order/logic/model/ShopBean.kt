package cn.wenhe9.order.logic.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 *@author DuJinliang
 *2021/11/18
 */
@Parcelize
data class ShopBean(val id : Int, val shopName : String, val saleNum : String, val offerPrice : String, val distributionCost : Int, val welfare : String, val time : String, val shopPic : String, val shopNotice : String) : Parcelable
