package com.ekades.products.core.network

import com.ekades.products.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

open class BaseApiClient<T>(private val classT: Class<T>) {

    companion object{
        const val CONNECTION_TIMEOUT: Long = 180L
        const val READ_TIMEOUT: Long = 180L
        const val WRITE_TIMEOUT: Long = 180L
    }

    open fun getApiClient(): T {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request = chain.request()
                        .newBuilder()
                        .removeHeader("User-Agent")
                        .header("Content-Type", "application/json")
                        .build()
                    return chain.proceed(request)
                }
            })
            .build()



        val retrofitBuilder = Retrofit.Builder().apply {
            baseUrl(BuildConfig.URL_SERVER)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }


        val retrofit = retrofitBuilder.build()
        return retrofit.create(classT)
    }
}