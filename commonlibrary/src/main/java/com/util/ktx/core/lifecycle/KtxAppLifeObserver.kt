package com.util.ktx.core.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class KtxAppLifeObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForeground() {
//        Ktx.app.toast("应用进入前台")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground() {
//        Ktx.app.toast("应用进入后台")
    }
}