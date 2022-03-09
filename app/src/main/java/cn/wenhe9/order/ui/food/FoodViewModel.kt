package cn.wenhe9.order.ui.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import cn.wenhe9.order.logic.Repository
import cn.wenhe9.order.logic.model.CarBean
import cn.wenhe9.order.logic.model.FoodBean

/**
 *@author DuJinliang
 *2021/11/19
 */
class FoodViewModel : ViewModel() {
    private val shopIdLiveData = MutableLiveData<Int>()

    val foodListLiveData = Transformations.switchMap(shopIdLiveData){ shopId ->
        Repository.getFoodList(shopId)
    }

    var totalMoney = 0
    var offerPrice = 0
    var distributionCost = 0

    val carList = mutableListOf<CarBean>()

    fun getFoodList(shopId : Int){
        shopIdLiveData.value = shopId
    }
}