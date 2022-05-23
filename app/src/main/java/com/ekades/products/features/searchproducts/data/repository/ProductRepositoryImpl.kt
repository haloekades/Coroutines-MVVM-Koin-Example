package com.ekades.products.features.searchproducts.data.repository

import com.ekades.products.features.searchproducts.data.datasource.rest.ProductRestDataStore
import com.ekades.products.features.searchproducts.data.datasource.rest.entities.ProductEntity
import com.ekades.products.features.searchproducts.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val productRestDataStore: ProductRestDataStore
) : ProductRepository {

    override fun getProducts(productName: String, start: Int): Flow<List<ProductEntity>> =
        productRestDataStore.getProducts(productName, start).map {
            it.data
        }
}
