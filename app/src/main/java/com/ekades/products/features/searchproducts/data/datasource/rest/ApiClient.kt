package com.ekades.products.features.searchproducts.data.datasource.rest

import com.ekades.products.core.network.BaseApiClient
import com.ekades.products.features.searchproducts.data.datasource.rest.interfaces.IProductApiClient

object PostApiClient : BaseApiClient<IProductApiClient>(IProductApiClient::class.java)