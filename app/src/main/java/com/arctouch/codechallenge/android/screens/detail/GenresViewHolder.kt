package com.arctouch.codechallenge.android.screens.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_genres.view.*
import kotlinx.android.synthetic.main.item_overview.view.*
import kotlinx.android.synthetic.main.item_movie.view.posterImageView

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class GenresViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: DetailModelItem.Genres) {
        itemView.genresTextView.text = model.genres
    }

    companion object {

        fun newInstance(
            inflater: LayoutInflater,
            parent: ViewGroup?
        ): GenresViewHolder {
            val view = inflater.inflate(R.layout.item_genres, parent, false)
            return GenresViewHolder(view)
        }

    }

}
