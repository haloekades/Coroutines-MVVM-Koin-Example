package com.carlosgub.coroutines.features.books.data.datasource.rest.entities

import com.google.gson.annotations.SerializedName

data class ProductEntity(
    val id: Int?,
    val name: String?,
    @SerializedName("image_uri")
    val imageUri: String?,
    val price: String?
)