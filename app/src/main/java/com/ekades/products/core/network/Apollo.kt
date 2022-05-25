package com.ekades.products.core.network

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain
import com.apollographql.apollo3.network.okHttpClient
import kotlinx.coroutines.flow.Flow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


private var instance: ApolloClient? = null

@JvmOverloads
fun apolloClient(context: Context): ApolloClient {
//    if (instance != null) {
//        return instance!!
//    }

    val okHttpClient = OkHttpClient().newBuilder()
//        .addInterceptor(AuthorizationInterceptor(context))
        .addNetworkInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                    .newBuilder()
                    .removeHeader("User-Agent")
//                    .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
                    .header("Content-Type", "application/json")
//                    .header("header-token-key", "header-token-value")
                    .build()
                return chain.proceed(request)
            }
        })
        .build()

    instance = ApolloClient.Builder()
        .serverUrl("https://gql.tokopedia.com")
//        .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
        .okHttpClient(okHttpClient)
//        .addHttpInterceptor(object : HttpInterceptor{
//            override suspend fun intercept(
//                request: HttpRequest,
//                chain: HttpInterceptorChain
//            ): HttpResponse {
//                return chain.proceed(request.newBuilder()
//                    .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36")
//                    .addHeader("Content-Type", "application/json")
//                    .build())
//            }
//
//        })
        .build()

    return instance!!
}

private class AuthorizationInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
//            .addHeader("Authorization", User.getToken(context) ?: "")
            .removeHeader("User-Agent")
            .header("Content-Type", "application/json")
            .build()

        return chain.proceed(request)
    }
}
