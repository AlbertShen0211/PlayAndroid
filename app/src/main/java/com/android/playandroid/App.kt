package com.android.playandroid

import android.app.Application
import android.content.Context
import com.android.childmode.network.HttpManager
import com.android.playandroid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import kotlin.properties.Delegates

class App : Application() {

    companion object {
        var appContext: Context by Delegates.notNull()
        val instance: App by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            App()
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        HttpManager.init(this)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}