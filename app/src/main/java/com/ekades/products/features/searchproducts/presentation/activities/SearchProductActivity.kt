package com.ekades.products.features.searchproducts.presentation.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekades.products.R
import com.ekades.products.core.utils.RecyclerViewInfiniteScrollListener
import com.ekades.products.features.searchproducts.presentation.adapters.RVProductAdapter
import com.ekades.products.features.searchproducts.presentation.viewmodel.SearchProductViewModel
import com.ekades.products.features.searchproducts.presentation.viewmodel.state.ProductsVS
import kotlinx.android.synthetic.main.search_products_activity.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchProductActivity : AppCompatActivity() {

    private val viewModel: SearchProductViewModel by viewModel()
    private val mAdapter = RVProductAdapter()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_products_activity)
        observeViewModel()
        setSearchListener()
        rvProducts.apply {
            layoutManager = LinearLayoutManager(this@SearchProductActivity)
            adapter = mAdapter
            addOnScrollListener(RecyclerViewInfiniteScrollListener {
                if (viewModel.isMaxProduct.not() && pbProducts.visibility == View.GONE) {
                    viewModel.getProductsByName(etSearchProduct.text.toString())
                }
            })
        }
    }

    private fun setSearchListener() {
        etSearchProduct.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotBlank()) {
                    mAdapter.clear()
                    viewModel.doSearchProduct(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun observeViewModel() {
        //Obtener los Posts
        viewModel.viewState.observe(this@SearchProductActivity) {
            when (it) {
                is ProductsVS.AddProduct -> {
                    mAdapter.add(it.productsVM)
                }
                is ProductsVS.ShowLoader -> {
                    if (it.showLoader) {
                        tvNotFound.visibility = View.GONE
                        pbProducts.visibility = View.VISIBLE
                        if (viewModel.start <= 0) {
                            rvProducts.visibility = View.GONE
                        }
                    } else {
                        pbProducts.visibility = View.GONE
                        rvProducts.visibility = View.VISIBLE
                    }
                }
                is ProductsVS.EmptyProduct -> {
                    pbProducts.visibility = View.GONE
                    rvProducts.visibility = View.GONE
                    tvNotFound.visibility = View.VISIBLE
                }
                is ProductsVS.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
