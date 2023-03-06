package com.example.sportzinteractive.network

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.example.sportzinteractive.constants.WebConstants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


object MyRetrofit {
    private const val REQUEST_TIMEOUT = 30L
    private var apiService: ClientApi? = null
    private var httpClient: OkHttpClient? = null

    fun getLocalApiClient(context: Context): ClientApi? {
        if (httpClient == null) {
            initOkHttp(context)
        }
        if (apiService == null) {
            apiService = Retrofit.Builder()
                .baseUrl(WebConstants.BASEURL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync()) // Using create async means all api calls are automatically created asynchronously using OkHttp's thread pool
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
                .create(ClientApi::class.java)
        }
        return apiService
    }
    private fun initOkHttp(context: Context) {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val manager = context.packageManager
        val info = manager.getPackageInfo(context.packageName, PackageManager.GET_ACTIVITIES)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            info.longVersionCode.toString()
        } else {
            info.versionCode.toString()

        }
        val httpBuilder = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                chain.proceed(request.build())
            }

        httpClient = httpBuilder.build()
    }
}