package com.carlosgub.coroutines.features.books.presentation.model.mapper

import com.carlosgub.coroutines.core.mapper.Mapper
import com.carlosgub.coroutines.features.books.data.datasource.rest.entities.ProductEntity
import com.carlosgub.coroutines.features.books.presentation.model.ProductVM

class ProductVMMapper : Mapper<ProductEntity, ProductVM> {

    override fun map(origin: ProductEntity): ProductVM {
        TODO("Not yet implemented")
    }

    override fun map(origin: List<ProductEntity>): List<ProductVM> {
        return origin.map {
            ProductVM(
                id = it.id,
                name = it.name,
                imageUri = it.imageUri,
                currentPrice = it.price ?: "",
                originalPrice = it.originalPrice ?: "",
                isDiscount = it.isDiscountProduct,
                badge = it.badge,
                city = it.city ?: "",
                discount = it.discountPercentage
            )
        }
    }
}