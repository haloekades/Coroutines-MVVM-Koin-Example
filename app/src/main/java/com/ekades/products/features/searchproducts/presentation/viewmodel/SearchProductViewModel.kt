package com.ekades.products.features.searchproducts.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import com.ekades.products.SearchProductQuery
import com.ekades.products.core.network.apolloClient
import com.ekades.products.core.platform.BaseViewModel
import com.ekades.products.core.utils.io
import com.ekades.products.core.utils.ui
import com.ekades.products.features.searchproducts.domain.interactor.GetProductsInteractor
import com.ekades.products.features.searchproducts.presentation.model.ProductVM
import com.ekades.products.features.searchproducts.presentation.model.mapper.ProductVMMapper
import com.ekades.products.features.searchproducts.presentation.viewmodel.state.ProductsVS
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchProductViewModel(
    private val getProductsInteractor: GetProductsInteractor
) : BaseViewModel() {

    val viewState: LiveData<ProductsVS> get() = mViewState
    private val mViewState = MutableLiveData<ProductsVS>()

    private val mProductVMMapper by lazy { ProductVMMapper() }

    var start = 0
    var searchJob: Job? = null
    var isMaxProduct = false
    var isUseGraphQL = false

    fun doSearchProduct(context: Context, productName: String) {
        isMaxProduct = false
        start = 0
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(1000)
            getProducts(context, productName)
        }
    }

    fun getProducts(context: Context, productName: String) {
        if (isUseGraphQL) {
            getProductsByGraphQL(context, productName)
        } else {
            getProductsByRestApi(productName)
        }
    }

    fun getProductsByRestApi(productName: String) {
        viewModelScope.launch {
            mViewState.value = ProductsVS.ShowLoader(true)
            try {
                io {
                    getProductsInteractor.execute(GetProductsInteractor.Params(productName, start))
                        .collect {
                            updateViewProducts(mProductVMMapper.map(it))
                        }
                }
            } catch (e: Exception) {
                ui {
                    mViewState.value = ProductsVS.Error(e.message)
                    mViewState.value = ProductsVS.ShowLoader(false)
                }
            }
        }
    }

    private fun updateViewProducts(productList: List<ProductVM>?) = viewModelScope.launch {
        io {
            isMaxProduct = start > 0 && productList.isNullOrEmpty()
            start += productList?.size ?: 0
        }
        ui {
            if (productList?.isNotEmpty() == true) {
                mViewState.value =
                    ProductsVS.AddProduct(productList)
                mViewState.value = ProductsVS.ShowLoader(false)
            } else if (start == 0) {
                mViewState.value = ProductsVS.EmptyProduct()
            }
        }
    }

    private fun getProductsByGraphQL(context: Context, productName: String) {
        viewModelScope.launch {
            mViewState.value = ProductsVS.ShowLoader(true)
            try {
                val query = "q=$productName&source=android&device=android&rows=10&start=$start"
                val response =
                    apolloClient(context).query(SearchProductQuery(Optional.Present(query)))
                        .execute()
                val productList: List<ProductVM>? =
                    response.data?.searchProduct?.products?.convertToProductList()
                updateViewProducts(productList)
            } catch (e: ApolloException) {
                mViewState.value = ProductsVS.Error(e.message)
                mViewState.value = ProductsVS.ShowLoader(false)
            }
        }
    }

    private fun List<SearchProductQuery.Product?>?.convertToProductList(): List<ProductVM>? {
        return this?.map { product ->
            ProductVM(
                id = product?.id?.toLong(),
                name = product?.name,
                imageUri = product?.image_url,
                currentPrice = product?.price ?: "",
                isDiscount = (product?.discount_percentage ?: 0) > 0,
                discount = product?.discount_percentage,
                originalPrice = product?.original_price ?: "",
                city = product?.shop?.city ?: "",
                badge = product?.badges?.getOrNull(0)?.image_url
            )
        }
    }
}