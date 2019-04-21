package com.arctouch.codechallenge.android.screens.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_overview.view.*
import kotlinx.android.synthetic.main.item_release_date.view.*
import kotlinx.android.synthetic.main.item_movie.view.posterImageView

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class ReleaseDateViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: DetailModelItem.ReleaseDate) {
        itemView.releaseDateTextView.text = model.releaseDate
    }

    companion object {

        fun newInstance(
            inflater: LayoutInflater,
            parent: ViewGroup?
        ): ReleaseDateViewHolder {
            val view = inflater.inflate(R.layout.item_release_date, parent, false)
            return ReleaseDateViewHolder(view)
        }

    }

}
