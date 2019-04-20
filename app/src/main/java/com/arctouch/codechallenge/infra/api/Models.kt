package com.arctouch.codechallenge.infra.api

import com.squareup.moshi.Json

data class GenreResponse(val genres: List<GenreItem>)

data class GenreItem(val id: Int, val name: String)

data class UpcomingMoviesResponse(
    val page: Int,
    val results: List<MovieItem>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

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
