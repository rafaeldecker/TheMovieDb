package com.arctouch.codechallenge.injection

import com.arctouch.codechallenge.entities.Genre
import com.arctouch.codechallenge.entities.Movie
import com.arctouch.codechallenge.infra.api.GenreItem
import com.arctouch.codechallenge.infra.api.MovieItem
import com.arctouch.codechallenge.infra.api.mappers.GenreMapper
import com.arctouch.codechallenge.infra.api.mappers.MovieMapper
import com.arctouch.codechallenge.utils.Mapper
import dagger.Binds
import dagger.Module

/**
 * Created by Rafael Decker on 2019-04-19.
 */

@Module
abstract class MappersModule {

    @Binds
    abstract fun bindGenreMapper(
        mapper: GenreMapper
    ): Mapper<GenreItem, Genre>

    @Binds
    abstract fun bindMovieMapper(
        mapper: MovieMapper
    ): Mapper<MovieItem, Movie>

}
