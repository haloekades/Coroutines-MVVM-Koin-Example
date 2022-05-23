package com.ekades.coroutines.features.searchproducts.data.datasource.rest

import com.ekades.coroutines.core.network.BaseApiClient
import com.ekades.coroutines.features.searchproducts.data.datasource.rest.interfaces.IPostApiClient

object PostApiClient : BaseApiClient<IPostApiClient>(IPostApiClient::class.java)