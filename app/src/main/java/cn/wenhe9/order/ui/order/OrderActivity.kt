package cn.wenhe9.order.ui.order

import android.app.Dialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import cn.wenhe9.order.R
import cn.wenhe9.order.databinding.ActivityOrderBinding
import cn.wenhe9.order.logic.model.CarBean

class OrderActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStyle()

        val list = intent.getParcelableArrayListExtra<CarBean>("carFoodList") as List<CarBean>

        val recyclerView = binding.orderHead.lvOrder
        recyclerView.adapter = OrderAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val totalMoney = intent.getIntExtra("totalMoney", 0)
        val distributionCost = intent.getIntExtra("distributionCost", 0)

        binding.orderHead.apply {
            tvCost.text = "￥$totalMoney"
            tvDistributionCost.text = "￥$distributionCost"
        }

        binding.payment.tvTotalCost.text = "￥${(totalMoney + distributionCost)}"

        binding.payment.tvPayment.setOnClickListener {
            val dialog = Dialog(this, R.style.Dialog_Style)
            dialog.setContentView(R.layout.qr_code)
            dialog.show()
        }
    }

    private fun setStyle(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.statusBarColor = resources.getColor(R.color.blue_color, null)
            }
        }

        binding.orderHead.titleBar.apply {
            tvTitle.text = "订单"
            tvBack.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                titleBar.setBackgroundColor(resources.getColor(R.color.blue_color, null))
            }
            tvBack.setOnClickListener {
                finish()
            }
        }
    }
}