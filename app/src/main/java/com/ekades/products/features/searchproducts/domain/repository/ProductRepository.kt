package com.ekades.products.features.searchproducts.domain.repository

import com.ekades.products.features.searchproducts.data.datasource.rest.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(productName: String, start: Int): Flow<List<ProductEntity>>
}