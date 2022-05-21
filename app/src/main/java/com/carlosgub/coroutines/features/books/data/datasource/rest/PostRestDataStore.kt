package com.carlosgub.coroutines.features.books.data.datasource.rest

import com.carlosgub.coroutines.features.books.data.datasource.rest.response.PostResponseData
import com.carlosgub.coroutines.features.books.data.datasource.rest.response.ProductsResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRestDataStore {

    fun getPosts(): Flow<PostResponseData> = flow {
        PostApiClient.getApiClient().getPosts().forEach {
            emit(it)
        }
    }

    fun getProducts(productName: String, start: Int): Flow<ProductsResponseData> = flow {
        emit(
            PostApiClient.getApiClient().getProducts(
                productName = productName,
                start = start
            )
        )
    }

    fun getPostById(id: String): Flow<PostResponseData> = flow {
        emit(PostApiClient.getApiClient().getPostById(id = id))
    }

}