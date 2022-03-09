package cn.wenhe9.order.ui.food

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cn.wenhe9.order.OrderApplication
import cn.wenhe9.order.R
import cn.wenhe9.order.logic.model.FoodBean
import com.bumptech.glide.Glide

/**
 *@author DuJinliang
 *2021/11/20
 */
class MenuAdapter(val dataList : List<FoodBean>, val onSelectedListener: OnSelectedListener) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val foodPic = view.findViewById<ImageView>(R.id.iv_food_pic)
        val foodName = view.findViewById<TextView>(R.id.tv_food_name)
        val taste = view.findViewById<TextView>(R.id.tv_taste)
        val saleNum = view.findViewById<TextView>(R.id.tv_sale_num)
        val price = view.findViewById<TextView>(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)

        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            val intent = Intent(OrderApplication.context, FoodActivity::class.java)
            intent.putExtra("food", dataList[position])
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            OrderApplication.context.startActivity(intent)
        }

        viewHolder.itemView.findViewById<Button>(R.id.btn_add_car).setOnClickListener{
            val position = viewHolder.bindingAdapterPosition
            onSelectedListener.onSelected(position)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodBean = dataList[position]

        holder.apply {
            foodName.text = foodBean.foodName
            taste.text = foodBean.taste
            saleNum.text = "月售${foodBean.saleNum}"
            price.text = "￥${foodBean.price.toString()}"
        }
        Glide.with(OrderApplication.context)
            .load(foodBean.foodPic)
            .error(R.mipmap.ic_launcher)
            .into(holder.foodPic)
    }

    override fun getItemCount() = dataList.size


    interface OnSelectedListener{
        fun onSelected(position : Int)
    }
}