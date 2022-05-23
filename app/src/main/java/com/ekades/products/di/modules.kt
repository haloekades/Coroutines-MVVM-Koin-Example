package com.ekades.coroutines.di

import com.ekades.coroutines.features.searchproducts.data.datasource.rest.PostRestDataStore
import com.ekades.coroutines.features.searchproducts.data.repository.PostRepositoryImpl
import com.ekades.coroutines.features.searchproducts.domain.interactor.GetProductsInteractor
import com.ekades.coroutines.features.searchproducts.domain.repository.PostRepository
import com.ekades.coroutines.features.searchproducts.presentation.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val productModule = module {

    //region ViewModel
    viewModel {
        PostsViewModel(get())
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
    single<PostRepository> {
        PostRepositoryImpl(get())
    }
    //endregion

    //region Datastore
    single {
        PostRestDataStore()
    }
    //endregion
}

val modules = listOf(productModule)