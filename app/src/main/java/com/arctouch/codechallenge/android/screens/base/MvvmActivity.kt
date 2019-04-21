package com.arctouch.codechallenge.android.screens.base

import android.os.Bundle
import com.arctouch.codechallenge.android.App
import com.arctouch.codechallenge.injection.ViewModelFactory
import com.arctouch.codechallenge.utils.extensions.observe
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-19.
 */

abstract class MvvmActivity<T : MvvmViewModel> : BaseActivity() {

    protected val injector
        get() = (application as App).applicationComponent

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        assignDependencies()
        super.onCreate(savedInstanceState)
        observeViewModelState()
        observeViewModelNavigator()
    }

    protected abstract fun assignDependencies()

    protected open fun observeViewModelState() {
        observe(viewModel.state, ::onViewModelStateChanged)
    }

    protected open fun observeViewModelNavigator() {
        observe(viewModel.navigator, ::onNavigate)
    }

    protected open fun onViewModelStateChanged(state: ViewModelState) {

    }

    protected open fun onNavigate(navigator: ViewModelNavigator) {

    }

}
