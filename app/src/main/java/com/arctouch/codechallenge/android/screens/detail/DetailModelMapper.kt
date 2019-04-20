package com.arctouch.codechallenge.android.screens.detail

import com.arctouch.codechallenge.entities.Movie
import com.arctouch.codechallenge.utils.Mapper
import com.arctouch.codechallenge.utils.MovieImageUrlBuilder
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class DetailModelMapper @Inject constructor(
    private val imageUrlBuilder: MovieImageUrlBuilder
): Mapper<Movie, DetailModel> {

    override fun map(param: Movie): DetailModel {
        val items = mutableListOf<DetailModelItem>()
        overviewItem(param)?.let { items.add(it) }
        genresItem(param)?.let { items.add(it) }
        releaseDateItem(param)?.let { items.add(it) }
        return DetailModel(
            title = param.title,
            backdropImageUrl = param.backdropPath?.let { imageUrlBuilder.buildBackdropUrl(it) },
            items = items
        )
    }

    override fun mapReverse(param: DetailModel): Movie {
        throw NotImplementedError("DetailModelMapper mapReverse")
    }

    private fun overviewItem(param: Movie): DetailModelItem.Overview? {
        if (param.posterPath != null || param.overview != null) {
            val posterUrl = param.posterPath?.let { imageUrlBuilder.buildPosterUrl(it) }
            return DetailModelItem.Overview(posterUrl, param.overview)
        }
        return null
    }

    private fun genresItem(param: Movie): DetailModelItem? {
        if (param.genres?.isNotEmpty() == true) {
            val genres = param.genres.joinToString(", ") { it.name }
            return DetailModelItem.Genres(genres)
        }
        return null
    }

    // TODO: format it in the future
    private fun releaseDateItem(param: Movie): DetailModelItem? =
        param.releaseDate?.let { DetailModelItem.ReleaseDate(it) }

}
