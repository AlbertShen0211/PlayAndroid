package com.android.playandroid.ui.login

import com.android.framework.ext.click
import com.android.framework.ext.start
import com.android.framework.ext.toast
import com.android.playandroid.R
import com.android.playandroid.viewmodel.LoginViewModel
import com.util.ktx.base.BaseVMActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_register.checkBox
import kotlinx.android.synthetic.main.activity_register.et_userName
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

class LoginActivity : BaseVMActivity<LoginViewModel>() {
    override fun initVM(): LoginViewModel = getViewModel()
    override fun getLayoutResId() = R.layout.activity_login

    //protected lateinit var binding: ViewDataBinding
    override fun initView() {
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

    }


    override fun initData() {
        Timber.e("initData")
        mBinding.root.btn_register.click {
            start<RegisterActivity>()
        }
        mBinding.root.tv_service_agree.click {
            Timber.e("service_agree")
            //start<WebViewActivity>()
        }
        mBinding.root.tv_run.click {
            toast("开发中")
        }

        mBinding.root.btn_login.click {
            val userName: String = et_userName.text.toString()
            val password: String = et_password.text.toString()
            //  mViewModel.login("ssssss@163.com", "ssss1112XXX")
            if (checkData()) {
                mViewModel.login(userName, password)
                //  mViewModel.login("P9631aH1zbggg@qq.com", "Qazwsx123")
            }
        }
    }

    private fun checkData(): Boolean {
        val userName: String = et_userName.text.toString()
        val password: String = et_password.text.toString()
      // Timber.e("mail: "+userName+", is: "+isEmail(userName))
        if (userName.isBlank() /*|| !isEmail(userName)*/) {
            toast(getString(R.string.right_mail))
            return false
        }
        if (password.isBlank()) {
            toast(getString(R.string.input))
            return false

        }
        /*if (!VerifyUtil.isLengthLegal(password) || !VerifyUtil.isContainAll(password)) {
            toast(getString(R.string.right_password))
            return false
        }*/

        if (!checkBox.isChecked) {
            toast(getString(R.string.service_agree))
            return false
        }

        return true
    }


    override fun startObserve() {

        mViewModel.loginBean.observe(this, androidx.lifecycle.Observer {


        })

        mViewModel.ok.observe(this, androidx.lifecycle.Observer {
            if (false == it) {
                mViewModel.err.observe(this, androidx.lifecycle.Observer {
                    toast(it)
                })
            }

        })

    }
}