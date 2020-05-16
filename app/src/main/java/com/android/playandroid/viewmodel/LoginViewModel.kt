package com.android.playandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.rxLifeScope
import com.android.playandroid.bean.LoginBean
import com.android.playandroid.repository.LoginRepository
import timber.log.Timber

class LoginViewModel(val repository: LoginRepository) : ViewModel() {

    private val _ok = MutableLiveData<Boolean>()
    val ok: LiveData<Boolean> get() = _ok

    private val _err = MutableLiveData<String>()
    val err: LiveData<String> get() = _err



    private val _loginBean = MutableLiveData<LoginBean>()
    val loginBean: LiveData<LoginBean> get() = _loginBean
    fun login(
        username: String, password: String
    ) = rxLifeScope.launch({
        val loginBean = repository.login(username, password)
        _loginBean.postValue(loginBean)
        _ok.value=true
    }, {
        _ok.value=false
        _err.value=it.message
        Timber.e("it.message: "+it.message)

    })


}
