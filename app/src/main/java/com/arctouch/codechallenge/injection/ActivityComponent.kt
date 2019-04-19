package com.arctouch.codechallenge.injection

import androidx.appcompat.app.AppCompatActivity
import com.arctouch.codechallenge.android.App
import dagger.Subcomponent

/**
 * Created by Rafael Decker on 2019-04-19.
 */

@ActivityScope
@Subcomponent(modules = [
    ActivityModule::class
])
interface ActivityComponent {

    companion object {

        fun init(activity: AppCompatActivity): ActivityComponent {
            val applicationComponent = (activity.application as App).applicationComponent
            return applicationComponent.plus(ActivityModule(activity))
        }

    }

}