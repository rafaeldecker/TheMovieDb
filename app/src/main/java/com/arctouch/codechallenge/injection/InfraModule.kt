package com.arctouch.codechallenge.injection

import com.arctouch.codechallenge.infra.logger.Logger
import com.arctouch.codechallenge.infra.logger.TimberLogger
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

}
