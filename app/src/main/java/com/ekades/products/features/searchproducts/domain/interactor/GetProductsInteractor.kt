package com.ekades.products.features.searchproducts.domain.interactor

import com.ekades.products.core.interactor.Interactor
import com.ekades.products.features.searchproducts.data.datasource.rest.entities.ProductEntity
import com.ekades.products.features.searchproducts.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsInteractor(
    private val postRepository: ProductRepository
) : Interactor<GetProductsInteractor.Params, Flow<List<ProductEntity>>> {

    override fun execute(params: Params): Flow<List<ProductEntity>> {
        return postRepository.getProducts(params.productName, params.start)
    }

    data class Params(val productName: String, val start: Int)
}