package cn.wenhe9.order.ui.shop

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.wenhe9.order.OrderApplication
import cn.wenhe9.order.R
import cn.wenhe9.order.logic.model.ShopBean
import cn.wenhe9.order.ui.food.ShopDetailActivity
import com.bumptech.glide.Glide

/**
 *@author DuJinliang
 *2021/11/19
 */
class ShopAdapter(val dataList : List<ShopBean>) : RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
         val shopPic = view.findViewById<ImageView>(R.id.iv_shop_pic)
         val shopName = view.findViewById<TextView>(R.id.tv_shop_name)
         val saleNum = view.findViewById<TextView>(R.id.tv_sale_num)
         val welfare = view.findViewById<TextView>(R.id.tv_welfare)
         val cost = view.findViewById<TextView>(R.id.tv_cost)
         val time = view.findViewById<TextView>(R.id.tv_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)

        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition

            val shopId = dataList[position].id

            val intent = Intent(OrderApplication.context, ShopDetailActivity::class.java)
            intent.putExtra("shopId", shopId)
            intent.putExtra("shop", dataList[position])
            //在非activity、service之类的外，执行跳转的逻辑需要添加FLAG_ACTIVITY_NEW_TASK标志
            //当然，也可以使用接口回调的方式，在activity中执行跳转t
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            OrderApplication.context.startActivity(intent)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopBean = dataList[position]

        holder.apply {
            shopName.text = shopBean.shopName
            saleNum.text = "月售${shopBean.saleNum}"
            cost.text = "起送￥${shopBean.offerPrice}|配送${shopBean.distributionCost}"
            time.text = shopBean.time
            welfare.text = shopBean.welfare
            Glide.with(OrderApplication.context)
                .load(shopBean.shopPic)
                .error(R.mipmap.ic_launcher)
                .into(shopPic)
        }
    }

    override fun getItemCount() = dataList.size

}