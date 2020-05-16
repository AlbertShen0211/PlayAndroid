package com.util.ktx.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        //初始化沉浸式
        initImmersionBar()
        initView()
//        setSupportActionBar(mToolbar)
        initData()
    }
    protected open fun initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).init()
    }

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}