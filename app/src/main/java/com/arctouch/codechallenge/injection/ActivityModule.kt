package com.arctouch.codechallenge.injection

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

/**
 * Created by Rafael Decker on 2019-04-19.
 */

@Module
class ActivityModule(
    private val activity: AppCompatActivity
) {

    @Provides
    @ActivityScope
    fun provideAppCompatActivity(): AppCompatActivity = activity

}
