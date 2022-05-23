package com.ekades.coroutines.features.searchproducts.data.repository

import com.ekades.coroutines.features.searchproducts.data.datasource.rest.PostRestDataStore
import com.ekades.coroutines.features.searchproducts.data.datasource.rest.entities.ProductEntity
import com.ekades.coroutines.features.searchproducts.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepositoryImpl(
    private val postRestDataStore: PostRestDataStore
) : PostRepository {

    override fun getProducts(productName: String, start: Int): Flow<List<ProductEntity>> =
        postRestDataStore.getProducts(productName, start).map {
            it.data
        }
}
