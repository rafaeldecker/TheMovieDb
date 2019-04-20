package com.arctouch.codechallenge.injection

import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.android.screens.base.MvvmViewModel
import com.arctouch.codechallenge.android.screens.detail.DetailViewModel
import com.arctouch.codechallenge.android.screens.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Rafael Decker on 2019-04-19.
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(vm: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(vm: DetailViewModel): ViewModel

}
