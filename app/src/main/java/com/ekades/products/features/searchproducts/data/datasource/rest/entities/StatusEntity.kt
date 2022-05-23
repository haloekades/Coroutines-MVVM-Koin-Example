package com.ekades.coroutines.features.searchproducts.data.datasource.rest.entities

import com.google.gson.annotations.SerializedName

data class StatusEntity(
    @SerializedName("error_code")
    val errorCode: Int?,
    val message: String?
)