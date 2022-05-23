package com.ekades.coroutines.features.searchproducts.presentation.model.mapper

import com.ekades.coroutines.core.mapper.Mapper
import com.ekades.coroutines.features.searchproducts.data.datasource.rest.entities.ProductEntity
import com.ekades.coroutines.features.searchproducts.presentation.model.ProductVM

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