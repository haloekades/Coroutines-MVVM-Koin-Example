package com.carlosgub.coroutines.features.books.domain.repository

import com.carlosgub.coroutines.features.books.data.datasource.rest.entities.ProductEntity
import com.carlosgub.coroutines.features.books.domain.model.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<PostEntity>
    fun getPostById(id: String): Flow<PostEntity>
    fun getProducts(productName: String, start: Int): Flow<List<ProductEntity>>
}