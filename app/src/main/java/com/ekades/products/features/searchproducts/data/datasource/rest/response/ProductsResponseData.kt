package com.ekades.products.features.searchproducts.data.datasource.rest.response

import com.ekades.products.features.searchproducts.data.datasource.rest.entities.HeaderEntity
import com.ekades.products.features.searchproducts.data.datasource.rest.entities.ProductEntity
import com.ekades.products.features.searchproducts.data.datasource.rest.entities.StatusEntity

data class ProductsResponseData(
    val status: StatusEntity,
    val header: HeaderEntity,
    val data: List<ProductEntity>
)