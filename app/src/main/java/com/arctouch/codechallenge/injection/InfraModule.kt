package com.arctouch.codechallenge.injection

import com.arctouch.codechallenge.infra.cache.GenresCache
import com.arctouch.codechallenge.infra.cache.GenresInMemoryCache
import com.arctouch.codechallenge.infra.logger.Logger
import com.arctouch.codechallenge.infra.logger.TimberLogger
import com.arctouch.codechallenge.infra.schedulers.RxSchedulerProvider
import com.arctouch.codechallenge.infra.schedulers.RxSchedulerProviderImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Rafael Decker on 2019-04-19.
 */

@Module
class InfraModule {

    @Provides
    fun providesLogger(
        logger: TimberLogger
    ): Logger = logger

    @Provides
    fun providesSchedulers(
        schedulers: RxSchedulerProviderImpl
    ): RxSchedulerProvider = schedulers

    @Provides
    fun providesGenresCache(): GenresCache = GenresInMemoryCache

}
