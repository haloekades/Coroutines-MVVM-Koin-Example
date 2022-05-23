package com.ekades.products.features.searchproducts.data.datasource.rest

import com.ekades.products.features.searchproducts.data.datasource.rest.response.ProductsResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRestDataStore {

    fun getProducts(productName: String, start: Int): Flow<ProductsResponseData> = flow {
        emit(
            PostApiClient.getApiClient().getProducts(
                productName = productName,
                start = start
            )
        )
    }
}