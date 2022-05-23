package com.ekades.products.di

import com.ekades.products.features.searchproducts.data.datasource.rest.ProductRestDataStore
import com.ekades.products.features.searchproducts.data.repository.ProductRepositoryImpl
import com.ekades.products.features.searchproducts.domain.interactor.GetProductsInteractor
import com.ekades.products.features.searchproducts.domain.repository.ProductRepository
import com.ekades.products.features.searchproducts.presentation.viewmodel.SearchProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val productModule = module {

    //region ViewModel
    viewModel {
        SearchProductViewModel(get())
    }
    //endregion

    //region Interactor
    single {
        GetProductsInteractor(
            get()
        )
    }
    //endregion

    //region Repository
    single<ProductRepository> {
        ProductRepositoryImpl(get())
    }
    //endregion

    //region Datastore
    single {
        ProductRestDataStore()
    }
    //endregion
}

val modules = listOf(productModule)