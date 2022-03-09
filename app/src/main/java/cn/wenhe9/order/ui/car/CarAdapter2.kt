package cn.wenhe9.order.ui.car

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.wenhe9.order.R
import cn.wenhe9.order.logic.model.CarBean
import cn.wenhe9.order.logic.model.FoodBean

/**
 *@author DuJinliang
 *2021/11/20
 */
class CarAdapter2(val dataList : List<CarBean>, val onSelectedAdd : (Int) -> Unit, val onSelectedMis: (Int) -> Unit) : RecyclerView.Adapter<CarAdapter2.ViewHolder>() {
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val foodName = view.findViewById<TextView>(R.id.tv_food_name)
        val foodCount = view.findViewById<TextView>(R.id.tv_food_count)
        val foodPrice = view.findViewById<TextView>(R.id.tv_food_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)

        val viewHolder = ViewHolder(view)

        viewHolder.itemView.findViewById<ImageView>(R.id.iv_add).setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            val carBean = dataList[position]
            onSelectedAdd(carBean.foodId)
        }

        viewHolder.itemView.findViewById<ImageView>(R.id.iv_minus).setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            val carBean = dataList[position]
            onSelectedMis(carBean.foodId)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val carBean = dataList[position]

        holder.apply {
            foodName.text = carBean.foodName
            foodCount.text = carBean.foodCount.toString()
            foodPrice.text = "￥${carBean.foodPrice*carBean.foodCount}"
        }
    }

    override fun getItemCount() = dataList.size
}