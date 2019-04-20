package com.arctouch.codechallenge.injection

import com.arctouch.codechallenge.utils.MovieImageUrlBuilder
import com.arctouch.codechallenge.utils.MovieImageUrlBuilderImpl
import dagger.Binds
import dagger.Module

/**
 * Created by Rafael Decker on 2019-04-20.
 */

@Module
abstract class UtilsModule {

    @Binds
    abstract fun bindMovieImageUrlBuilder(
        builder: MovieImageUrlBuilderImpl
    ): MovieImageUrlBuilder

}
