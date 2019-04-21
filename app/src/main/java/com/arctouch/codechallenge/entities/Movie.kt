package com.arctouch.codechallenge.entities

/**
 * Created by Rafael Decker on 2019-04-20.
 */

data class Movie(
    val id: Long,
    val title: String,
    val overview: String?,
    val genres: List<Genre>?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?
)