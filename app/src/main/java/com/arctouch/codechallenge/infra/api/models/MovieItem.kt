package com.arctouch.codechallenge.infra.api.models

import com.squareup.moshi.Json

/**
 * Created by Rafael Decker on 2019-04-20.
 */

data class MovieItem(
    val id: Long,
    val title: String,
    val overview: String?,
    val genres: List<GenreItem>?,
    @Json(name = "genre_ids") val genreIds: List<Int>?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "release_date") val releaseDate: String?
)