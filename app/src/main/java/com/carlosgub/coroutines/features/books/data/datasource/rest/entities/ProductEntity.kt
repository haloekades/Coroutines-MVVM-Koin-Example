package com.carlosgub.coroutines.features.books.data.datasource.rest.entities

import com.google.gson.annotations.SerializedName

data class ProductEntity(
    val id: Long?,
    val name: String?,
    @SerializedName("image_uri")
    val imageUri: String?,
    val price: String?,
    @SerializedName("discount_percentage")
    val discountPercentage: Int?,
    @SerializedName("original_price")
    val originalPrice: String?,
    val shop: ShopEntity?,
    val badges: List<BadgeEntity>?
) {
    val city: String?
        get() = shop?.city

    val badge: String?
        get() = badges?.firstOrNull()?.takeIf { it.show }?.imageUrl

    val isDiscountProduct: Boolean
        get() = (discountPercentage ?: 0) > 0
}