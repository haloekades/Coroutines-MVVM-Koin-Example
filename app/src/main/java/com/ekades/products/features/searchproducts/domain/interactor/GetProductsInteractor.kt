package com.ekades.coroutines.features.searchproducts.domain.interactor

import com.ekades.coroutines.core.interactor.Interactor
import com.ekades.coroutines.features.searchproducts.data.datasource.rest.entities.ProductEntity
import com.ekades.coroutines.features.searchproducts.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetProductsInteractor(
    private val postRepository: PostRepository
) : Interactor<GetProductsInteractor.Params, Flow<List<ProductEntity>>> {

    override fun execute(params: Params): Flow<List<ProductEntity>> {
        return postRepository.getProducts(params.productName, params.start)
    }

    data class Params(val productName: String, val start: Int)
}