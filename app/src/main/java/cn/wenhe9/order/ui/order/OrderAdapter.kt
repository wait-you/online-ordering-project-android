package cn.wenhe9.order.ui.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.wenhe9.order.OrderApplication
import cn.wenhe9.order.R
import cn.wenhe9.order.logic.model.CarBean
import cn.wenhe9.order.logic.model.FoodBean
import com.bumptech.glide.Glide

/**
 *@author DuJinliang
 *2021/11/20
 */
class OrderAdapter(val dataList : List<CarBean>) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val foodPic = view.findViewById<ImageView>(R.id.iv_food_pic)
        val foodName = view.findViewById<TextView>(R.id.tv_food_name)
        val foodCount = view.findViewById<TextView>(R.id.tv_count)
        val foodMoney = view.findViewById<TextView>(R.id.tv_money)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carBean = dataList[position]

        holder.apply {
            foodName.text = carBean.foodName
            foodCount.text = "x${carBean.foodCount}"
            foodMoney.text = "￥${(carBean.foodCount * carBean.foodPrice)}"
        }
        Glide.with(OrderApplication.context)
            .load(carBean.foodPic)
            .error(R.mipmap.ic_launcher)
            .into(holder.foodPic)
    }

    override fun getItemCount() = dataList.size
}