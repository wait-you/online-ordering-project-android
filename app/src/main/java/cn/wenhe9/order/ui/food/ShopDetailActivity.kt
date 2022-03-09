package cn.wenhe9.order.ui.food

import android.app.Dialog
import android.content.Intent
import android.icu.util.Measure
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cn.wenhe9.order.OrderApplication
import cn.wenhe9.order.R
import cn.wenhe9.order.databinding.ActivityShopDetailBinding
import cn.wenhe9.order.logic.model.CarBean
import cn.wenhe9.order.logic.model.ShopBean
import cn.wenhe9.order.ui.car.CarAdapter
import cn.wenhe9.order.ui.car.CarAdapter2
import cn.wenhe9.order.ui.order.OrderActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.coroutineScope
import java.io.Serializable

class ShopDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityShopDetailBinding

    private lateinit var adapter : MenuAdapter2

    private val viewModel by lazy {
        ViewModelProvider(this).get(FoodViewModel::class.java)
    }

    private lateinit var carAdapter : CarAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitleBarStyle()

        initTitle()

        loadData()

        initLayout()
    }

    //初始化标题内容
    private fun initTitle(){
        val shopInfo = intent.getParcelableExtra<ShopBean>("shop") as ShopBean
        binding.shopDetail.apply {
            tvShopName.text = shopInfo.shopName
            tvNotice.text = shopInfo.shopNotice
            tvTime.text = shopInfo.time

        }
        viewModel.offerPrice = shopInfo.offerPrice.toInt()
        viewModel.distributionCost = shopInfo.distributionCost
        binding.shopCar.tvNotEnough.text = "￥${shopInfo.offerPrice}起送"
        Glide.with(this)
            .load(shopInfo.shopPic)
            .error(R.mipmap.ic_launcher)
            .into(binding.shopDetail.ivShopPic)
    }

    //清空购物车，包括将下方的样式还原
    private fun clearCarList(){
        viewModel.apply {
            carList.clear()
            totalMoney = 0
        }
        carAdapter.notifyDataSetChanged()
        binding.carList.rlCarList.visibility = View.GONE

        binding.shopCar.apply {
            tvNotEnough.text = "￥${viewModel.offerPrice}起送"
            car.apply {
                ivShopCar.setImageResource(R.drawable.shop_car_empty)
                tvCount.text = ""
                tvCount.visibility = View.GONE
            }
            tvMoney.text = "未选购商品"
            tvSettleAccounts.visibility = View.GONE
            tvNotEnough.visibility = View.VISIBLE
            tvDistributionCost.text = ""
        }
    }

    //获取指定foodId在carList的索引
    private fun indexOfViewModel(foodId : Int) : Int {
        for (carBean in viewModel.carList) {
            if (carBean.foodId==foodId){
                return viewModel.carList.indexOf(carBean)
            }
        }
        return 0
    }

    //清空购物车弹出及事件
    private fun dialogClear(){
        val dialog = Dialog(this, R.style.Dialog_Style)
        dialog.setContentView(R.layout.dialog_clear)
        dialog.show()

        val clear = dialog.findViewById<TextView>(R.id.tv_clear)
        val cancel = dialog.findViewById<TextView>(R.id.tv_cancel)

        clear.setOnClickListener {
            clearCarList()
            dialog.dismiss()
        }

        cancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    //设置系统标题栏样式
    private fun setTitleBarStyle(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.statusBarColor = resources.getColor(R.color.shop_bg_color, null)
            }
        }


        binding.titleBar.apply {
            tvTitle.text = "店铺详情"
            tvBack.visibility = View.VISIBLE
            tvBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun loadData(){
        val shopId = intent.getIntExtra("shopId", 1)

        viewModel.getFoodList(shopId)

        viewModel.foodListLiveData.observe(this){ foodList ->
            val result = foodList.getOrNull()

            if (result != null){
                adapter = MenuAdapter2(result){ position ->
                    val foodBean = result[position]

                    val carBean = CarBean(foodBean.id, foodBean.foodName, 1, foodBean.price, foodBean.foodPic)

                    var flag = true
                    for (bean in viewModel.carList) {
                        if (bean.foodId==carBean.foodId){
                            bean.foodCount+=1
                            flag = false
                            break
                        }
                    }
                    if (flag){
                        viewModel.carList.add(carBean)
                    }
                    carAdapter.notifyDataSetChanged()

                    viewModel.totalMoney+=foodBean.price
                    binding.shopCar.apply {
                        tvMoney.text = "￥${viewModel.totalMoney}"

                        if (viewModel.totalMoney > viewModel.offerPrice){
                            tvNotEnough.visibility = View.GONE
                            tvSettleAccounts.visibility = View.VISIBLE
                            tvDistributionCost.apply {
                                text = "另需配送费￥${viewModel.distributionCost}"
                                visibility = View.VISIBLE
                            }
                        }

                        car.apply {
                            ivShopCar.setImageResource(R.drawable.shop_car)
                            tvCount.text = viewModel.carList.size.toString()
                            tvCount.visibility = View.VISIBLE
                        }
                    }
                }
                binding.menuList.adapter = adapter
            }else{
                Toast.makeText(this, "数据拿取失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initLayout(){
        val menuListView = binding.menuList
        menuListView.adapter = null
        menuListView.layoutManager = LinearLayoutManager(this)

        val carListView = binding.carList.lvCar
        carAdapter = CarAdapter2(
            viewModel.carList,
            {
                foodId ->
            val index = indexOfViewModel(foodId)
            val carBean = viewModel.carList[index]
            carBean.foodCount+=1
            viewModel.totalMoney+=carBean.foodPrice
            binding.shopCar.tvMoney.text = "￥${viewModel.totalMoney}"
            carAdapter.notifyDataSetChanged()

            if (viewModel.totalMoney > viewModel.offerPrice){
                binding.shopCar.apply {
                    tvNotEnough.visibility = View.GONE
                    tvSettleAccounts.visibility = View.VISIBLE
                    tvDistributionCost.apply {
                        text = "另需配送费￥${viewModel.distributionCost}"
                        visibility = View.VISIBLE
                    }
                }
            }
        },
        { foodId ->
                val index = indexOfViewModel(foodId)
                val carBean = viewModel.carList[index]
                if (carBean.foodCount>1){
                    carBean.foodCount-=1
                    viewModel.totalMoney-=carBean.foodPrice
                    binding.shopCar.tvMoney.text = "￥${viewModel.totalMoney.toString()}"

                    if (viewModel.totalMoney < viewModel.offerPrice){
                        binding.shopCar.apply {
                            tvNotEnough.visibility = View.VISIBLE
                            tvSettleAccounts.visibility = View.GONE
                            tvDistributionCost.apply {
                                text = ""
                                visibility = View.GONE
                            }
                        }
                    }
                }else{
                    viewModel.carList.removeAt(index)
                    if (viewModel.carList.size==0){
                        clearCarList()
                    }else if (viewModel.totalMoney < viewModel.offerPrice){
                        binding.shopCar.apply {
                            car.tvCount.text=viewModel.carList.size.toString()
                            tvNotEnough.visibility = View.VISIBLE
                            tvSettleAccounts.visibility = View.GONE
                            tvDistributionCost.apply {
                                text = ""
                                visibility = View.GONE
                            }
                        }
                    }else{
                        viewModel.totalMoney-=carBean.foodPrice
                        binding.shopCar.apply {
                            car.tvCount.text = viewModel.carList.size.toString()
                            tvMoney.text = "￥${viewModel.totalMoney.toString()}"
                        }
                    }

                }
                carAdapter.notifyDataSetChanged()
            })

        carListView.adapter = carAdapter
        carListView.layoutManager = LinearLayoutManager(this)

        binding.carList.tvClear.setOnClickListener {
            dialogClear()
        }

        binding.shopCar.car.ivShopCar.setOnClickListener {
            if (binding.shopCar.tvMoney.text == "未选购商品"){
                Toast.makeText(this, "您还未选购商品", Toast.LENGTH_SHORT).show()
            }else{
                binding.carList.rlCarList.apply {
                    if (visibility == View.GONE){
                        visibility = View.VISIBLE
                        val animation =
                            AnimationUtils.loadAnimation(OrderApplication.context, R.anim.slide_bottom_to_top)
                        startAnimation(animation)
                    }else{
                        visibility = View.GONE
                    }
                }
            }
        }

        binding.shopCar.tvSettleAccounts.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("totalMoney", viewModel.totalMoney)
            intent.putExtra("distributionCost", viewModel.distributionCost)
            val carList = viewModel.carList
            intent.putExtra("carFoodList", carList as Serializable)
            startActivity(intent)
        }
    }
}