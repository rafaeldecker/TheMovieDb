package com.arctouch.codechallenge.android.screens.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_overview.view.*
import kotlinx.android.synthetic.main.item_movie.view.posterImageView

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class OverviewViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: DetailModelItem.Overview) {
        itemView.overviewTextView.text = model.overview
        Glide.with(itemView)
            .load(model.imageUrl)
            .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
            .into(itemView.posterImageView)
    }

    companion object {

        fun newInstance(
            inflater: LayoutInflater,
            parent: ViewGroup?
        ): OverviewViewHolder {
            val view = inflater.inflate(R.layout.item_overview, parent, false)
            return OverviewViewHolder(view)
        }

    }

}
