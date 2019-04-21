package com.arctouch.codechallenge.android.screens.home

import android.os.Bundle
import android.view.View
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.android.screens.base.MvvmActivity
import com.arctouch.codechallenge.android.screens.base.ViewModelNavigator
import com.arctouch.codechallenge.android.screens.base.ViewModelState
import com.arctouch.codechallenge.android.screens.base.withNavigator
import com.arctouch.codechallenge.android.screens.detail.DetailActivity
import com.arctouch.codechallenge.android.widgets.EndlessRecyclerViewScrollListener
import com.arctouch.codechallenge.injection.ActivityComponent
import com.arctouch.codechallenge.utils.ClickHandler
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.include_error_layout.*

class HomeActivity : MvvmActivity<HomeViewModel>(),
    ClickHandler<HomeModel> {

    override val viewModel: HomeViewModel by lazy {
        viewModelFactory.get<HomeViewModel>(this)
    }

    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupViews()
    }

    private fun setupViews() {
        retryButton.setOnClickListener {
            viewModel.retry()
        }
        scrollListener = EndlessRecyclerViewScrollListener(
            onLoadMore = {
                viewModel.onLoadMore()
            }
        )
        recyclerView.addOnScrollListener(scrollListener)
        adapter = HomeAdapter(this)
        recyclerView.adapter = adapter
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
        adapter.movies = list
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
