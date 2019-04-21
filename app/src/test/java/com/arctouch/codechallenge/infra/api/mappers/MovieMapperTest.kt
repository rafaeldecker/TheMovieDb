package com.arctouch.codechallenge.infra.api.mappers

import com.arctouch.codechallenge.entities.Genre
import com.arctouch.codechallenge.infra.api.models.GenreItem
import com.arctouch.codechallenge.infra.api.models.MovieItem
import com.arctouch.codechallenge.infra.cache.GenresCache
import com.squareup.moshi.Json
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class MovieMapperTest {

    @Mock
    lateinit var genresCache: GenresCache

    private lateinit var genreMapper: GenreMapper

    private lateinit var movieMapper: MovieMapper

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        genreMapper = GenreMapper()
        movieMapper = MovieMapper(genresCache, genreMapper)
    }

    @Test
    fun `test mapping when genre ids exists and genres array not`() {
        val movieItem = MovieItem(
            id = 10,
            title = "title",
            overview = "overview",
            genres = null,
            genreIds = listOf(1, 2, 3),
            posterPath = "posterPath",
            backdropPath = "backdropPath",
            releaseDate = "releaseDate"
        )

        Mockito.`when`(genresCache.genres).thenReturn(cachedGenres)
        val movie = movieMapper.map(movieItem)
        Assert.assertEquals(movie.id, movieItem.id)
        Assert.assertEquals(movie.title, movieItem.title)
        Assert.assertEquals(movie.overview, movieItem.overview)
        Assert.assertEquals(movie.genres!!.size, 3)
        Assert.assertEquals(movie.posterPath, movieItem.posterPath)
        Assert.assertEquals(movie.backdropPath, movieItem.backdropPath)
        Assert.assertEquals(movie.releaseDate, movieItem.releaseDate)
    }

    @Test
    fun `test mapping when genre ids not exists and genres array exists`() {
        val movieItem = MovieItem(
            id = 10,
            title = "title",
            overview = "overview",
            genres = genresItems,
            genreIds = null,
            posterPath = "posterPath",
            backdropPath = "backdropPath",
            releaseDate = "releaseDate"
        )

        Mockito.`when`(genresCache.genres).thenReturn(null)
        val movie = movieMapper.map(movieItem)
        Assert.assertEquals(movie.id, movieItem.id)
        Assert.assertEquals(movie.title, movieItem.title)
        Assert.assertEquals(movie.overview, movieItem.overview)
        Assert.assertEquals(movie.genres!!.size, 3)
        Assert.assertEquals(movie.posterPath, movieItem.posterPath)
        Assert.assertEquals(movie.backdropPath, movieItem.backdropPath)
        Assert.assertEquals(movie.releaseDate, movieItem.releaseDate)
    }

    @Test
    fun `test mapping when there is no genres`() {
        val movieItem = MovieItem(
            id = 10,
            title = "title",
            overview = "overview",
            genres = null,
            genreIds = null,
            posterPath = "posterPath",
            backdropPath = "backdropPath",
            releaseDate = "releaseDate"
        )

        Mockito.`when`(genresCache.genres).thenReturn(null)
        val movie = movieMapper.map(movieItem)
        Assert.assertEquals(movie.id, movieItem.id)
        Assert.assertEquals(movie.title, movieItem.title)
        Assert.assertEquals(movie.overview, movieItem.overview)
        Assert.assertNull(movie.genres)
        Assert.assertEquals(movie.posterPath, movieItem.posterPath)
        Assert.assertEquals(movie.backdropPath, movieItem.backdropPath)
        Assert.assertEquals(movie.releaseDate, movieItem.releaseDate)
    }

    companion object {
        val cachedGenres = listOf(
            Genre(1, "One"),
            Genre(2, "Two"),
            Genre(3, "Three")
        )

        val genresItems = listOf(
            GenreItem(1, "One"),
            GenreItem(2, "Two"),
            GenreItem(3, "Three")
        )
    }

}