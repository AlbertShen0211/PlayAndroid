package com.android.playandroid.login

import com.android.framework.ext.click
import com.android.framework.ext.toast
import com.android.playandroid.R
import com.android.playandroid.databinding.ActivityRegisterBinding
import com.android.playandroid.util.VerifyUtil
import com.android.playandroid.viewmodel.LoginViewModel
import com.util.ktx.base.BaseVMActivity
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*
import kotlinx.android.synthetic.main.title_layout.view.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

class RegisterActivity : BaseVMActivity<LoginViewModel>() {


    override fun initVM(): LoginViewModel = getViewModel()

    override fun getLayoutResId() = R.layout.activity_register

    override fun initView() {
        //  binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        // binding.lifecycleOwner = this
        (mBinding as ActivityRegisterBinding).viewModel = mViewModel
        mBinding.root.toolbar.setNavigationIcon(R.mipmap.register_close)
        mBinding.root.toolbar.setMyCenterTitle(getString(R.string.register), true)
        mBinding.root.toolbar.setMySettingText(getString(R.string.login))

    }

    override fun initData() {
        //  bitmap()
        mBinding.root.toolbar.setNavigationOnClickListener {
            finish()
        }


        mBinding.root.toolbar.setSettingTextOnClickListener {
            // start<LoginActivity>()
        }

        mBinding.root.btn_register.click {

            //  start<TipActivity>()
            if (checkData()) {

            }

        }
        mBinding.root.tv_service_agree.click {
            Timber.e("service_agree")
            //  start<WebViewActivity>()
        }

      /*  mBinding.root.iv_refresh.click {

        }
*/
    }

    private fun checkData(): Boolean {
        val email: String = et_email.text.toString()
        val password: String = et_reg_pwd.text.toString()
        val userName: String = et_userName.text.toString()
        val verify: String = et_verify.text.toString()

        if (email.isBlank() || !VerifyUtil.isEmail(email)) {
            toast(getString(R.string.right_mail))
            return false
        }
        if (password.isBlank() /*|| !isLengthLegal(password) || !isContainAll(password)*/) {
            toast(getString(R.string.right_password))
            return false
        }
        if (userName.isBlank()) {
            toast(getString(R.string.input_user))
            return false
        }
        if (verify.isBlank() || 4 != verify.length) {
            toast(getString(R.string.input_verify))
            return false
        }
        if (!checkBox.isChecked) {
            toast(getString(R.string.agree_service_1))
            return false
        }

        return true
    }


    override fun startObserve() {


    }
}