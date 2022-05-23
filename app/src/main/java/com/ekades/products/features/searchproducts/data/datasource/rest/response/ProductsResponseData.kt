package com.ekades.coroutines.features.searchproducts.data.datasource.rest.response

import com.ekades.coroutines.features.searchproducts.data.datasource.rest.entities.HeaderEntity
import com.ekades.coroutines.features.searchproducts.data.datasource.rest.entities.ProductEntity
import com.ekades.coroutines.features.searchproducts.data.datasource.rest.entities.StatusEntity

data class ProductsResponseData(
    val status: StatusEntity,
    val header: HeaderEntity,
    val data: List<ProductEntity>
)