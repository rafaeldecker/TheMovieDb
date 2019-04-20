package com.arctouch.codechallenge.android.screens.home

import com.arctouch.codechallenge.entities.Movie
import com.arctouch.codechallenge.utils.Mapper
import com.arctouch.codechallenge.utils.MovieImageUrlBuilder
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class HomeModelMapper @Inject constructor(
    private val urlBuilder: MovieImageUrlBuilder
): Mapper<Movie, HomeModel> {

    override fun map(param: Movie): HomeModel =
        with(param) {
            HomeModel(
                id = id,
                title = title,
                genres = genres.orEmpty().joinToString(separator = ", ") { it.name },
                releaseDate = releaseDate.orEmpty(),
                posterUrl = posterPath?.let { urlBuilder.buildPosterUrl(it) }
            )
        }

    override fun mapReverse(param: HomeModel): Movie {
        throw NotImplementedError("MovieModelMapper mapReverse")
    }
}