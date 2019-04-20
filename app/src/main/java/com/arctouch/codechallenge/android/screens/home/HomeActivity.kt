package com.arctouch.codechallenge.android.screens.home

import android.os.Bundle
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.android.screens.base.MvvmActivity
import com.arctouch.codechallenge.android.screens.base.ViewModelNavigator
import com.arctouch.codechallenge.android.screens.base.ViewModelState
import com.arctouch.codechallenge.android.screens.base.withNavigator
import com.arctouch.codechallenge.android.screens.detail.DetailActivity
import com.arctouch.codechallenge.injection.ActivityComponent
import com.arctouch.codechallenge.utils.ClickHandler
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.include_error_layout.*

class HomeActivity : MvvmActivity<HomeViewModel>(),
    ClickHandler<HomeModel> {

    override val viewModel: HomeViewModel by lazy {
        viewModelFactory.get<HomeViewModel>(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        retryButton.setOnClickListener {
            viewModel.retry()
        }
    }

    override fun assignDependencies() {
        ActivityComponent.init(this).inject(this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewModelStateChanged(state: ViewModelState) {
        when (state) {
            is ViewModelState.Data<*> -> showDataState(state.data as List<HomeModel>)
            ViewModelState.Loading -> showLoadingState()
            is ViewModelState.Error -> showErrorState()
        }
    }

    override fun onNavigate(navigator: ViewModelNavigator) {
        withNavigator(navigator) {
            when (it) {
                is NavigateToMovieDetail -> {
                    DetailActivity.launch(
                        context = this,
                        id = it.movieId
                    )
                }
            }
        }
    }

    override fun onClick(item: HomeModel) {
        viewModel.onItemClicked(item)
    }

    private fun showDataState(list: List<HomeModel>) {
        errorLayout.visibility = View.GONE
        progressBar.visibility = View.GONE
        recyclerView.adapter = HomeAdapter(list, this)
    }

    private fun showErrorState() {
        progressBar.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
    }

    private fun showLoadingState() {
        errorLayout.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

}
