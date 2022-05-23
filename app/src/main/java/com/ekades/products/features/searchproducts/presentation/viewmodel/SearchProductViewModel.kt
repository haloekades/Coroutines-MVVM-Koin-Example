package com.ekades.coroutines.features.searchproducts.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ekades.coroutines.core.platform.BaseViewModel
import com.ekades.coroutines.core.utils.io
import com.ekades.coroutines.core.utils.ui
import com.ekades.coroutines.features.searchproducts.domain.interactor.GetProductsInteractor
import com.ekades.coroutines.features.searchproducts.presentation.model.mapper.ProductVMMapper
import com.ekades.coroutines.features.searchproducts.presentation.viewmodel.state.ProductsVS
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostsViewModel(
    private val getProductsInteractor: GetProductsInteractor
) : BaseViewModel() {

    val viewState: LiveData<ProductsVS> get() = mViewState
    private val mViewState = MutableLiveData<ProductsVS>()

    private val mProductVMMapper by lazy { ProductVMMapper() }

    var start = 0
    var searchJob: Job? = null
    var isMaxProduct = false

    fun doSearchProduct(productName: String) {
        isMaxProduct = false
        start = 0
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(2000)
            getProductsByName(productName)
        }
    }

    fun getProductsByName(productName: String) {
        viewModelScope.launch {
            mViewState.value = ProductsVS.ShowLoader(true)
            try {
                io {
                    Log.d("get_product", "name:$productName start:$start ")
                    getProductsInteractor.execute(GetProductsInteractor.Params(productName, start))
                        .collect {
                            Log.d("get_product", "product_size : ${it.size}")
                            io {
                                isMaxProduct = start > 0 && it.isEmpty()
                                start += it.size
                            }
                            ui {
                                if (it.isEmpty() && start == 0) {
                                    mViewState.value = ProductsVS.EmptyProduct()
                                } else {
                                    mViewState.value =
                                        ProductsVS.AddProduct(mProductVMMapper.map(it))
                                    mViewState.value = ProductsVS.ShowLoader(false)
                                }
                            }
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
}