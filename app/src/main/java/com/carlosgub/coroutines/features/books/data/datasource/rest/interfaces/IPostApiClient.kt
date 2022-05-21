package com.carlosgub.coroutines.features.books.data.datasource.rest.interfaces

import com.carlosgub.coroutines.features.books.data.datasource.rest.response.PostResponseData
import com.carlosgub.coroutines.features.books.data.datasource.rest.response.ProductsResponseData
import kotlinx.coroutines.CoroutineStart
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface IPostApiClient {
    @GET("/posts")
    suspend fun getPosts(): List<PostResponseData>

    @GET("/posts/{id}")
    suspend fun getPostById(@Path("id") id: String): PostResponseData

    @GET("/search/v1/product")
    suspend fun getProducts(
        @Query("q") productName: String,
        @Query("source") source: String = "android",
        @Query("rows") rows: Int = 10,
        @Query("start") start: Int = 0
    ): ProductsResponseData
}