package com.ekades.coroutines.features.searchproducts.data.datasource.rest.interfaces

import com.ekades.coroutines.features.searchproducts.data.datasource.rest.response.ProductsResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface IPostApiClient {

    @GET("/search/v1/product")
    suspend fun getProducts(
        @Query("q") productName: String,
        @Query("source") source: String = "android",
        @Query("rows") rows: Int = 10,
        @Query("start") start: Int = 0
    ): ProductsResponseData

}