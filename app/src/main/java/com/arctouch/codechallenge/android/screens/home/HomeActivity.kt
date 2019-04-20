package com.arctouch.codechallenge.android.screens.home

import android.os.Bundle
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.android.screens.base.MvvmActivity
import com.arctouch.codechallenge.android.screens.base.ViewModelState
import com.arctouch.codechallenge.injection.ActivityComponent
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : MvvmActivity<HomeViewModel>() {

    override val viewModel: HomeViewModel by lazy {
        viewModelFactory.get<HomeViewModel>(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
    }

    override fun assignDependencies() {
        ActivityComponent.init(this).inject(this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewModelStateChanged(state: ViewModelState) {
        when (state) {
            is ViewModelState.Data<*> -> showDataState(state.data as List<MovieModel>)
            ViewModelState.Loading -> showLoadingState()
            is ViewModelState.Error -> showErrorState()
        }
    }

    private fun showDataState(list: List<MovieModel>) {
        errorLayout.visibility = View.GONE
        progressBar.visibility = View.GONE
        recyclerView.adapter = HomeAdapter(list)
    }

    private fun showErrorState() {
        errorLayout.visibility = View.VISIBLE
    }

    private fun showLoadingState() {
        errorLayout.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

}
