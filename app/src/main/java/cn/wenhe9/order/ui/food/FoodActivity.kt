package cn.wenhe9.order.ui.food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.wenhe9.order.R
import cn.wenhe9.order.databinding.ActivityFoodBinding
import cn.wenhe9.order.logic.model.FoodBean
import com.bumptech.glide.Glide

class FoodActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFoodBinding
    private lateinit var bean : FoodBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bean = intent.getParcelableExtra<FoodBean>("food") as FoodBean

        setData()
    }

    private fun setData(){
        binding.tvFoodName.text = bean.foodName
        binding.tvPrice.text = "￥${bean.price}"
        binding.tvSaleNum.text = "月售${bean.saleNum}"
        Glide.with(this)
            .load(bean.foodPic)
            .error(R.mipmap.ic_launcher)
            .into(binding.ivFoodPic)

    }
}