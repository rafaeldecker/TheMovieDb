package com.arctouch.codechallenge.injection

import com.arctouch.codechallenge.domain.FetchAndStoreGenresUseCase
import com.arctouch.codechallenge.domain.FetchAndStoreGenresUseCaseImpl
import com.arctouch.codechallenge.domain.FetchUpcomingMoviesUseCase
import com.arctouch.codechallenge.domain.FetchUpcomingMoviesUseCaseImpl
import dagger.Binds
import dagger.Module

/**
 * Created by Rafael Decker on 2019-04-19.
 */

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindFetchAndStoreGenresUseCase(
        useCase: FetchAndStoreGenresUseCaseImpl
    ): FetchAndStoreGenresUseCase

    @Binds
    abstract fun bindFetchUpcomingMoviesUseCase(
        useCase: FetchUpcomingMoviesUseCaseImpl
    ): FetchUpcomingMoviesUseCase

}
