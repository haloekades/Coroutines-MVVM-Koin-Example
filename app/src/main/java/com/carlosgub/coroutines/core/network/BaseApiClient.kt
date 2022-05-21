package com.carlosgub.coroutines.core.network

import android.webkit.WebSettings
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
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

//    private fun getOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(object : Interceptor {
//                @Throws(IOException::class)
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val request: Request = chain.request()
//                        .newBuilder()
//                        .header("Content-Type", "application/json")
//                        .build()
//                    return chain.proceed(request)
//                }
//            }).build()
//    }

    open fun getApiClient(): T {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
//            .addInterceptor(httpLoggingInterceptor)
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
//            baseUrl(BuildConfig.URL_SERVER)
            baseUrl("https://ace.tokopedia.com")
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }


        val retrofit = retrofitBuilder.build()
        return retrofit.create(classT)
    }
}