package com.ekades.coroutines.features.searchproducts.data.datasource.rest.entities

import com.google.gson.annotations.SerializedName

data class HeaderEntity(
    @SerializedName("total_data")
    val totalData: Int?,
    @SerializedName("additional_params")
    val additionalParams: String?,
    @SerializedName("process_time")
    val processTime: Double?
)