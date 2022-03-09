package cn.wenhe9.order.ui.shop

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cn.wenhe9.order.R
import cn.wenhe9.order.databinding.ActivityShopBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.system.exitProcess


class ShopActivity : AppCompatActivity() {

    private lateinit var binding : ActivityShopBinding

    private val viewModel by lazy {
        ViewModelProvider(this).get(ShopViewModel::class.java)
    }

    private val adapter by lazy {
        ShopAdapter(viewModel.shopList())
    }

    private val refreshLayout by lazy {
        binding.refresh
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitleBarStyle()

        //判断是否已加载，防止activity翻转数据重新加载，因为viewModel的生命周期长于activity，所以没有必要重新加载
        if (viewModel.isLoad()){
            viewModel.load()
        }

        val recycler = binding.recycler
        recycler.adapter = null
        recycler.layoutManager = LinearLayoutManager(this)

        //观察数据，当变化时拿取数据更换recyclerView的adapter
        viewModel.shopListLiveData.observe(this){shopList ->
            val result = shopList.getOrNull()

            if (result!=null){
                recycler.adapter = adapter
            }else{
                recycler.adapter = null
                Toast.makeText(this, "数据拿取失败", Toast.LENGTH_SHORT).show()
            }

            refreshLayout.isRefreshing = false
        }

        refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            refreshLayout.isRefreshing = true
        }

    }

    //设置标题栏样式
    private fun setTitleBarStyle(){
        binding.titleBar.apply {
            tvTitle.text = "店铺"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                titleBar.setBackgroundColor(resources.getColor(R.color.blue_color, null))
            }
            tvBack.visibility = View.GONE
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.statusBarColor = resources.getColor(R.color.blue_color, null)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis() - viewModel.exitTime > 2000){
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                viewModel.exitTime = System.currentTimeMillis()
            }else{
                finish()
                exitProcess(0)
            }
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

}