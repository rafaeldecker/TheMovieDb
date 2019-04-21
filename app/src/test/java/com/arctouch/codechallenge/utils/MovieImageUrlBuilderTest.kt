package com.arctouch.codechallenge.utils

import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class MovieImageUrlBuilderTest  {

    private lateinit var builder: MovieImageUrlBuilder

    @Before
    fun setup() {
        builder = MovieImageUrlBuilderImpl()
    }

    @Test
    fun `test builder creates right poster url`() {
        val url = builder.buildPosterUrl(POSTER_PATH)
        val desiredUrl = "$POSTER_URL$POSTER_PATH?api_key=$API_KEY"
        Assert.assertEquals(desiredUrl, url)
    }

    @Test
    fun `test builder creates right backdrop url`() {
        val url = builder.buildBackdropUrl(BACKDROP_PATH)
        val desiredUrl = "$BACKDROP_URL$BACKDROP_PATH?api_key=$API_KEY"
        Assert.assertEquals(desiredUrl, url)
    }

    companion object {
        private const val POSTER_PATH = "/path/to/poster"
        private const val BACKDROP_PATH = "/path/to/backdrop"
        private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
        private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
        private const val API_KEY = "1f54bd990f1cdfb230adb312546d765d"
    }

}
