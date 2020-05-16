package com.util.ktx.core.lifecycle

import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * A custom Handler attached with the LifeCycle
 */
class KtxHandler(lifecycleOwner: LifecycleOwner, callback: Callback) : Handler(callback), LifecycleObserver {

    private val mLifecycleOwner: LifecycleOwner = lifecycleOwner

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        removeCallbacksAndMessages(null)
        mLifecycleOwner.lifecycle.removeObserver(this)
    }
}