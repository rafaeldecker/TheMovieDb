package com.arctouch.codechallenge.android.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.utils.ClickHandler
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeAdapter(
    private val movies: List<HomeModel>,
    private var clickHandler: ClickHandler<HomeModel>? = null
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View,
        private var clickHandler: ClickHandler<HomeModel>? = null
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: HomeModel) {
            itemView.setOnClickListener {
                clickHandler?.onClick(movie)
            }
            itemView.titleTextView.text = movie.title
            itemView.genresTextView.text = movie.genres
            itemView.releaseDateTextView.text = movie.releaseDate
            Glide.with(itemView)
                .load(movie.posterUrl)
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(itemView.posterImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view, clickHandler)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])
}
