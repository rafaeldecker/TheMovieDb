package com.arctouch.codechallenge.utils

interface MovieImageUrlBuilder {

    fun buildPosterUrl(posterPath: String): String
    fun buildBackdropUrl(backdropPath: String): String
}
