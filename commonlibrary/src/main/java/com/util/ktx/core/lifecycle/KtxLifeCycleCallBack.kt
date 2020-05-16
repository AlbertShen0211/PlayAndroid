package com.util.ktx.core.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.util.ktx.ext.loge

class KtxLifeCycleCallBack : Application.ActivityLifecycleCallbacks {


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        KtxManager.pushActivity(activity)
        "onActivityCreated : ${activity.localClassName}".loge()
    }

    override fun onActivityStarted(activity: Activity) {
        "onActivityStarted : ${activity.localClassName}".loge()
    }

    override fun onActivityResumed(activity: Activity) {
        "onActivityResumed : ${activity.localClassName}".loge()
    }

    override fun onActivityPaused(activity: Activity) {
        "onActivityPaused : ${activity.localClassName}".loge()
    }


    override fun onActivityDestroyed(activity: Activity) {
        "onActivityDestroyed : ${activity.localClassName}".loge()
        KtxManager.popActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity) {
        "onActivityStopped : ${activity.localClassName}".loge()
    }


}