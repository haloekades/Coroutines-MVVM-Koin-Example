package com.carlosgub.coroutines.features.books.data.datasource.rest.response

import com.carlosgub.coroutines.features.books.data.datasource.rest.entities.HeaderEntity
import com.carlosgub.coroutines.features.books.data.datasource.rest.entities.ProductEntity
import com.carlosgub.coroutines.features.books.data.datasource.rest.entities.StatusEntity

data class ProductsResponseData(
    val status: StatusEntity,
    val header: HeaderEntity,
    val data: List<ProductEntity>
)