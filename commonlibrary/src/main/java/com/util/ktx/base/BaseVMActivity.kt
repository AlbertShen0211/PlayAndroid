package com.util.ktx.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.gyf.immersionbar.ImmersionBar
import com.util.ktx.R
import kotlinx.android.synthetic.main.title_layout.*

abstract class BaseVMActivity<VM : ViewModel>(/*useDataBinding: Boolean = true*/) : AppCompatActivity() {

   // private val _useBinding = useDataBinding
    protected lateinit var mBinding: ViewDataBinding
    lateinit var mViewModel: VM
    //lateinit var mToolbar: CustomToolBar
    lateinit var mImmersionBar: ImmersionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = initVM()
        mBinding = DataBindingUtil.setContentView<ViewDataBinding>(this, getLayoutResId())
        //初始化沉浸式
        if (isImmersionBarEnabled()) initImmersionBar()
       // mToolbar = mBinding.root.toolbar as CustomToolBar
        mBinding.lifecycleOwner = this
        startObserve()
       /* if (_useBinding) {
            mBinding = DataBindingUtil.setContentView<ViewDataBinding>(this, getLayoutResId())
            mBinding.lifecycleOwner = this
        } else */
            //setContentView(getLayoutResId())
        initView()

        initData()
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected open fun isImmersionBarEnabled(): Boolean {
        return  true
       // return this !is MainActivity
}

    protected open fun initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
        if (toolbar != null) {
            mImmersionBar.titleBar(toolbar)
        }
        mImmersionBar.statusBarDarkFont(true)
    //    mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init()
      //  mImmersionBar.init()
        //设置共同沉浸式样式

        //设置共同沉浸式样式
       ImmersionBar.with(this).init()
    }


    open fun getLayoutResId(): Int = 0
    abstract fun initVM(): VM
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()

}