package com.arctouch.codechallenge.android

import android.app.Application
import com.arctouch.codechallenge.infra.logger.Logger
import com.arctouch.codechallenge.injection.ApplicationComponent
import com.arctouch.codechallenge.injection.DaggerApplicationComponent
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-19.
 */

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()
        startServices()
    }

    private fun startServices() {
        startDagger()
        startLogger()
    }

    private fun startDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        applicationComponent.inject(this)
    }

    private fun startLogger() {
        logger.start()
    }

}
