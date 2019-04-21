package com.arctouch.codechallenge.injection

import javax.inject.Scope

/**
 * Created by Rafael Decker on 2019-04-19.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope