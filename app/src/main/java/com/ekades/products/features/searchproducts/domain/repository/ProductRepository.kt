package com.ekades.coroutines.features.searchproducts.domain.repository

import com.ekades.coroutines.features.searchproducts.data.datasource.rest.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getProducts(productName: String, start: Int): Flow<List<ProductEntity>>
}