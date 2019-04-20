package com.arctouch.codechallenge.injection

import androidx.appcompat.app.AppCompatActivity
import com.arctouch.codechallenge.android.App
import com.arctouch.codechallenge.android.screens.home.HomeActivity
import dagger.Subcomponent

/**
 * Created by Rafael Decker on 2019-04-19.
 */

@ActivityScope
@Subcomponent(modules = [
    ActivityModule::class
])
interface ActivityComponent {

    fun inject(activity: HomeActivity)

    companion object {

        fun init(activity: AppCompatActivity): ActivityComponent {
            val applicationComponent = (activity.application as App).applicationComponent
            return applicationComponent.plus(ActivityModule(activity))
        }

    }

}
