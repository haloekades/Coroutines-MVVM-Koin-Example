package com.carlosgub.coroutines.features.books.presentation.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlosgub.coroutines.R
import com.carlosgub.coroutines.features.books.presentation.model.ProductVM
import kotlinx.android.synthetic.main.product_item.view.*

class RVProductAdapter : RecyclerView.Adapter<RVProductAdapter.ViewHolder>() {

    private val mList: MutableList<ProductVM> = mutableListOf()

    fun add(lstPostVM: List<ProductVM>) {
        mList.addAll(lstPostVM)
        notifyItemInserted(this.itemCount)
    }

    fun clear() {
        mList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
    )

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(postVM: ProductVM) {
            with(itemView) {
                Glide.with(context).load(postVM.imageUri).into(ivProduct)
                tvProductName.text = postVM.name
                tvProductPrice.text = postVM.currentPrice
                if (postVM.isDiscount) {
                    discountView.visibility = View.VISIBLE
                    tvDiscountProduct.text =
                        context.getString(R.string.value_percentage, postVM.discount?.toString())
                    tvOriginalPrice.apply {
                        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                        text = postVM.originalPrice
                    }
                } else {
                    discountView.visibility = View.GONE
                }

                postVM.badge?.takeIf { it.isNotBlank() }?.apply {
                    Glide.with(context).load(this).into(ivBadges)
                    ivBadges.visibility = View.VISIBLE
                } ?: kotlin.run {
                    ivBadges.visibility = View.GONE
                }

                tvProductCity.text = postVM.city
            }
        }
    }
}