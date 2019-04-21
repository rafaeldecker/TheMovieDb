package com.arctouch.codechallenge.android.widgets

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class EndlessRecyclerViewScrollListener(
    val onLoadMore: () -> Unit
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        val totalItemCount = linearLayoutManager.itemCount
        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == totalItemCount - 1) {
            onLoadMore()
        }
    }

}