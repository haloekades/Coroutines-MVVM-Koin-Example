package com.carlosgub.coroutines.features.books.domain.interactor

import com.carlosgub.coroutines.core.interactor.Interactor
import com.carlosgub.coroutines.features.books.data.datasource.rest.entities.ProductEntity
import com.carlosgub.coroutines.features.books.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetProductsInteractor(
    private val postRepository: PostRepository
) : Interactor<GetProductsInteractor.Params, Flow<List<ProductEntity>>> {

    override fun execute(params: Params): Flow<List<ProductEntity>> {
        return postRepository.getProducts(params.productName, params.start)
    }

    data class Params(val productName: String, val start: Int)
}