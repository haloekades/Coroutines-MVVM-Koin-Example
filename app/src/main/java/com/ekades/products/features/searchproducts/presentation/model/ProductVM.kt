package com.ekades.products.features.searchproducts.presentation.model

data class ProductVM(
    val id: Long?,
    val name: String?,
    val imageUri: String?,
    val currentPrice: String,
    val isDiscount: Boolean,
    val discount : Int?,
    val originalPrice: String,
    val badge: String?,
    val city: String
)