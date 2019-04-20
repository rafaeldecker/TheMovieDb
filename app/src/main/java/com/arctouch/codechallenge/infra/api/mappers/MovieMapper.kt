package com.arctouch.codechallenge.infra.api.mappers

import com.arctouch.codechallenge.entities.Movie
import com.arctouch.codechallenge.infra.api.MovieItem
import com.arctouch.codechallenge.infra.cache.GenresCache
import com.arctouch.codechallenge.utils.Mapper
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class MovieMapper @Inject constructor(
    private val genresCache: GenresCache
): Mapper<MovieItem, Movie> {

    override fun map(param: MovieItem): Movie =
        with(param) {
            Movie(
                id = id,
                title = title,
                overview = overview,
                posterPath = posterPath,
                backdropPath = backdropPath,
                releaseDate = releaseDate,
                genres = genresCache.genres.filter { genreIds?.contains(it.id) == true }
            )
        }

    override fun mapReverse(param: Movie): MovieItem =
            throw NotImplementedError("MovieMapper mapReverse not implemented")

}
