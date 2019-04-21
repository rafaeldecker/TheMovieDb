package com.arctouch.codechallenge.injection

import com.arctouch.codechallenge.android.screens.detail.DetailModel
import com.arctouch.codechallenge.android.screens.detail.DetailModelMapper
import com.arctouch.codechallenge.android.screens.home.HomeModel
import com.arctouch.codechallenge.android.screens.home.HomeModelMapper
import com.arctouch.codechallenge.entities.Genre
import com.arctouch.codechallenge.entities.Movie
import com.arctouch.codechallenge.infra.api.mappers.GenreMapper
import com.arctouch.codechallenge.infra.api.mappers.MovieMapper
import com.arctouch.codechallenge.infra.api.models.GenreItem
import com.arctouch.codechallenge.infra.api.models.MovieItem
import com.arctouch.codechallenge.utils.Mapper
import dagger.Binds
import dagger.Module

/**
 * Created by Rafael Decker on 2019-04-19.
 */

@Module
abstract class MappersModule {

    // Domain

    @Binds
    abstract fun bindGenreMapper(
        mapper: GenreMapper
    ): Mapper<GenreItem, Genre>

    @Binds
    abstract fun bindMovieMapper(
        mapper: MovieMapper
    ): Mapper<MovieItem, Movie>

    // Home screen

    @Binds
    abstract fun bindHomeModelMapper(
        mapper: HomeModelMapper
    ): Mapper<Movie, HomeModel>

    // Detail screen

    @Binds
    abstract fun bindDetailModelMapper(
        mapper: DetailModelMapper
    ): Mapper<Movie, DetailModel>

}
