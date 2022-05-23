package com.carlosgub.coroutines.features.books.data.datasource.rest.entities

import com.google.gson.annotations.SerializedName

data class BadgeEntity(
    @SerializedName("image_url")
    val imageUrl: String?,
    val show: Boolean
)