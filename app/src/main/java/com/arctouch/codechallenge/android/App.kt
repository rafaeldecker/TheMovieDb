package com.arctouch.codechallenge.android

import android.app.Application
import com.arctouch.codechallenge.injection.ApplicationComponent
import com.arctouch.codechallenge.injection.DaggerApplicationComponent

/**
 * Created by Rafael Decker on 2019-04-19.
 */

class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        startServices()
    }

    private fun startServices() {
        startDagger()
    }

    private fun startDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        applicationComponent.inject(this)
    }

}
