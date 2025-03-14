package com.madslangkilde.repository_base_server

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

abstract class BaseServerConnector<T> {

    private var clazz: Class<T>? = null

    private val requestTag: String = "Movies_App_Tag"

    var mRetrofit: Retrofit? = null

    protected open var api: T? = null
        get() {
            if (field == null) {
                if (mRetrofit == null) {
                    mRetrofit = getRetrofit()
                }
                api = mRetrofit!!.create(clazz!!)
            }
            return field
        }

    private val gsonConfigure: Gson
        get() = GsonBuilder().setLenient().create()

    init {
        clazz = getClazz()
    }

    private fun getRetrofit(): Retrofit {
        val builder = getClientWithHeaderInfo()
        val mHttpClient = builder.build()
        return buildRetrofit(getBaseUrl(), gsonConfigure, mHttpClient)
    }

    private fun getClientWithHeaderInfo(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)

        val interceptorBuilder = ServerRequestInterceptor.Builder()
        interceptorBuilder.setExtraParams(getExtraParamsForHeaders())
        interceptorBuilder.setRequestTag(requestTag)
        builder.addInterceptor(interceptorBuilder.build())

        return builder
    }

    private fun buildRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .client(client)
            .build()
    }

    abstract fun getClazz(): Class<T>

    protected abstract fun getBaseUrl(): String

    protected open fun getExtraParamsForHeaders(): HashMap<String, String> = HashMap()
}