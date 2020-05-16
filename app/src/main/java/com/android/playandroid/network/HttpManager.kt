package com.android.childmode.network

import android.app.Application
import androidx.databinding.library.baseAdapters.BuildConfig
import okhttp3.OkHttpClient
import rxhttp.RxHttpPlugins
import rxhttp.wrapper.cahce.CacheMode
import rxhttp.wrapper.cookie.CookieStore
import rxhttp.wrapper.param.Param
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.ssl.SSLSocketFactoryImpl
import rxhttp.wrapper.ssl.X509TrustManagerImpl
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/**
 * Created by Albert on 2020/5/7.
 * Description:
 */
class HttpManager {
    companion object RxHttpParam {
        fun init(context: Application?) {
            //val file = File(context!!.externalCacheDir, "RxHttpCookie")
            val trustAllCert: X509TrustManager =
                X509TrustManagerImpl()
            val sslSocketFactory: SSLSocketFactory =
                SSLSocketFactoryImpl(trustAllCert)
            val client = OkHttpClient.Builder()
                .cookieJar(rxhttp.wrapper.cookie.CookieStore())
                //.cookieJar(CookieStore(file))
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory, trustAllCert) //添加信任证书
                .hostnameVerifier(HostnameVerifier { hostname: String?, session: SSLSession? -> true }) //忽略host验证
                //            .followRedirects(false)  //禁制OkHttp的重定向操作，我们自己处理重定向
                //            .addInterceptor(new RedirectInterceptor())
                //            .addInterceptor(new TokenInterceptor())
                .build()
            //RxHttp初始化，自定义OkHttpClient对象，非必须
            RxHttp.init(client, BuildConfig.DEBUG)

            //设置缓存策略，非必须
            //   val cacheFile = File(context.externalCacheDir, "RxHttpCache")
            /* RxHttpPlugins.setCache(
                 cacheFile,
                 1000 * 100.toLong(),
                 CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE
             )*/
            // RxHttpPlugins.setExcludeCacheKeys("time") //设置一些key，不参与cacheKey的组拼

            //设置数据解密/解码器，非必须
//        RxHttp.setResultDecoder(s -> s);

            //设置全局的转换器，非必须
//        RxHttp.setConverter(FastJsonConverter.create());

            //设置公共参数，非必须
            RxHttp.setOnParamAssembly { p: Param<*>? ->
                /*根据不同请求添加不同参数，子线程执行，每次发送请求前都会被回调
                            如果希望部分请求不回调这里，发请求前调用Param.setAssemblyEnabled(false)即可
                             */
                val method = p!!.method
                if (method!!.isGet) { //Get请求
                } else if (method.isPost) { //Post请求
                    // p.addHeader("deviceType", "android")
                }
                 p.add("versionName", "1.0.0") //添加公共参数
                /* .add("time", System.currentTimeMillis())*/
               /* p.addHeader(
                    "User-Agent",
                    "ChaseDream-Forum" + (android.os.Build.MODEL + android.os.Build.VERSION.RELEASE + DeviceIdUtil.getDeviceId(
                        context
                    ))
                )*/ //添加公共请求头
            }
        }
    }
}