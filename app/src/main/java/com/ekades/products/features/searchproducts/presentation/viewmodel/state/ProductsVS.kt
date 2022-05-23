package com.ekades.coroutines.features.searchproducts.presentation.viewmodel.state

import com.ekades.coroutines.features.searchproducts.presentation.model.ProductVM

sealed class ProductsVS {
    class AddProduct(val productsVM: List<ProductVM>) : ProductsVS()
    class EmptyProduct() : ProductsVS()
    class Error(val message: String?) : ProductsVS()
    class ShowLoader(val showLoader: Boolean) : ProductsVS()
}