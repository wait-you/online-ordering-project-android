package cn.wenhe9.order.ui.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import cn.wenhe9.order.logic.Repository
import cn.wenhe9.order.logic.model.ShopBean

/**
 *@author DuJinliang
 *2021/11/19
 */
class ShopViewModel : ViewModel() {

    //判断数据是否已经加载
    private val isLoad = MutableLiveData<Boolean>()

    val shopListLiveData = Transformations.switchMap(isLoad){
        Repository.getShopList()
    }

    var exitTime = 0L

    //当isLoad为true时，数据值变换，执行shopListLiveData的数据转换获取数据
    fun load(){
        isLoad.value = false
    }

    //只有在第一次加载的时候因为没有值，所以返回true
    fun isLoad() : Boolean{
        return isLoad.value ?: true
    }

    fun refresh(){
        isLoad.value = true
    }

    fun shopList() :List<ShopBean> {
        return shopListLiveData.value?.getOrNull() ?: mutableListOf<ShopBean>()
    }
}