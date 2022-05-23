package com.ekades.coroutines.core.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Create an Endless Recyclerview Scroll Listener
 * @param onReachEnd calls when RecyclerView scrolled to bottom
 */
class RecyclerViewInfiniteScrollListener(
    private val onReachEnd: () -> Unit
) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val llm = recyclerView.layoutManager as? LinearLayoutManager
        if (dy > 0 && llm != null) {
            val visibleItemCount = llm.childCount
            val totalItemCount = llm.itemCount
            val pastVisibleItems = llm.findFirstVisibleItemPosition()

            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                onReachEnd.invoke()
            }
        }
    }
}