package com.arctouch.codechallenge.injection

import android.app.Application
import android.content.Context
import com.arctouch.codechallenge.android.App
import dagger.Module
import dagger.Provides

/**
 * Created by Rafael Decker on 2019-04-19.
 */

@Module
class ApplicationModule {

    @Provides
    fun provideContext(app: App): Context = app

    @Provides
    fun provideApplication(app: App): Application = app

}
