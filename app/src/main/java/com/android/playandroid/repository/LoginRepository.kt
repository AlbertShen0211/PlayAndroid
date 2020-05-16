package com.android.playandroid.repository

import com.android.playandroid.bean.LoginBean
import rxhttp.toClass
import rxhttp.wrapper.param.RxHttp

class LoginRepository() {
     suspend fun login(username: String, password: String): LoginBean {
           return  RxHttp.postForm("https://forum.chasedream.com/api/mobile/index.php?mobile=no&version=1&module=login&loginsubmit=yes&loginfield=auto&submodule=checkpost")
                .add("username", username)
                .add("password", password)
                .toClass<LoginBean>()
                .await()
    }



}