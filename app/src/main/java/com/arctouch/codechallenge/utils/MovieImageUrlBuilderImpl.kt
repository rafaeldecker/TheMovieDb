package com.arctouch.codechallenge.utils

import com.arctouch.codechallenge.BuildConfig
import com.arctouch.codechallenge.infra.api.TmdbApi
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class MovieImageUrlBuilderImpl @Inject constructor(

): MovieImageUrlBuilder {

    override fun buildPosterUrl(posterPath: String): String {
        return POSTER_URL + posterPath + "?api_key=" + BuildConfig.API_KEY
    }

    override fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?api_key=" + BuildConfig.API_KEY
    }

    companion object {
        private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
        private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
    }

}
