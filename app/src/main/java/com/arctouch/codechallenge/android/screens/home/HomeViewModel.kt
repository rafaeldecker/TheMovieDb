package com.arctouch.codechallenge.android.screens.home

import com.arctouch.codechallenge.android.screens.base.MvvmViewModel
import com.arctouch.codechallenge.android.screens.base.ViewModelNavigator
import com.arctouch.codechallenge.android.screens.base.ViewModelState
import com.arctouch.codechallenge.domain.FetchAndStoreGenresUseCase
import com.arctouch.codechallenge.domain.FetchUpcomingMoviesUseCase
import com.arctouch.codechallenge.entities.Movie
import com.arctouch.codechallenge.infra.logger.Logger
import com.arctouch.codechallenge.infra.schedulers.RxSchedulerProvider
import com.arctouch.codechallenge.utils.Mapper
import com.arctouch.codechallenge.utils.extensions.observeOnMainThread
import com.arctouch.codechallenge.utils.extensions.saveMainThread
import com.arctouch.codechallenge.utils.extensions.subscribeOnIo
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-19.
 */

data class NavigateToMovieDetail(val movieId: Long)

class HomeViewModel @Inject constructor(
    private val rxSchedulerProvider: RxSchedulerProvider,
    private val fetchAndStoreGenresUseCase: FetchAndStoreGenresUseCase,
    private val fetchUpcomingMoviesUseCase: FetchUpcomingMoviesUseCase,
    private val HomeModelMapper: Mapper<Movie, HomeModel>,
    private val logger: Logger
) : MvvmViewModel() {

    private var data: ArrayList<HomeModel> = ArrayList()
    private var currentOffset: Long = 0
    private var maxPages = Long.MAX_VALUE

    init {
        loadData()
    }

    fun retry() {
        loadData()
    }

    private fun loadData() {
        addDisposable(
            fetchAndStoreGenresUseCase.run()
                .doOnSubscribe { updateState(ViewModelState.Loading) }
                .subscribeOnIo(rxSchedulerProvider)
                .andThen(fetchNextPageObservable())
                .observeOnMainThread (rxSchedulerProvider)
                .subscribe({
                    handleResult(it)
                }, {
                    handleError(it)
                })
        )
    }

    fun onItemClicked(item: HomeModel) {
        val target = NavigateToMovieDetail(item.id)
        updateNavigator(ViewModelNavigator.Navigate(target))
    }

    fun onLoadMore() {
        logger.d("Current page: $currentOffset maxPages: $maxPages")
        if (canLoadMore()) {
            updateState(ViewModelState.Loading)
            addDisposable(
                fetchNextPageObservable()
                    .saveMainThread(rxSchedulerProvider)
                    .subscribe({
                        handleResult(it)
                    },{
                        handleError(it)
                    })
            )
        }
    }

    private fun fetchNextPageObservable() =
        fetchUpcomingMoviesUseCase.fetch((currentOffset + 1))
            .doOnNext {
                currentOffset = it.page
                maxPages = it.totalPages
                logger.d("Current page: $currentOffset maxPages: $maxPages")
            }
            .map { HomeModelMapper.mapList(it.data) }

    private fun handleResult(it: List<HomeModel>) {
        data.addAll(it)
        updateState(ViewModelState.Data(data))
    }

    private fun canLoadMore(): Boolean =
        state.value != ViewModelState.Loading && currentOffset < maxPages

}
