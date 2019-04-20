package com.arctouch.codechallenge.android.screens.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.io.InvalidObjectException

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class DetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataSource: List<DetailModelItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        val data = dataSource[position]
        return when(data) {
            is DetailModelItem.Overview -> OVERVIEW_TYPE
            is DetailModelItem.Genres -> GENRES_TYPE
            is DetailModelItem.ReleaseDate -> RELEASE_DATE_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            OVERVIEW_TYPE -> OverviewViewHolder.newInstance(inflater, parent)
            GENRES_TYPE -> GenresViewHolder.newInstance(inflater, parent)
            RELEASE_DATE_TYPE -> ReleaseDateViewHolder.newInstance(inflater, parent)
            else -> throw InvalidObjectException("Could not find view holder for the type $viewType")
        }
    }

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is OverviewViewHolder -> holder.bind(dataSource[position] as DetailModelItem.Overview)
            is GenresViewHolder -> holder.bind(dataSource[position] as DetailModelItem.Genres)
            is ReleaseDateViewHolder -> holder.bind(dataSource[position] as DetailModelItem.ReleaseDate)
        }
    }

    companion object {
        private const val OVERVIEW_TYPE = 0
        private const val GENRES_TYPE = 1
        private const val RELEASE_DATE_TYPE = 2
    }

}
