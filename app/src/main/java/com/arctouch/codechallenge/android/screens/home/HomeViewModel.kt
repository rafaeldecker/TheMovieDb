package com.arctouch.codechallenge.android.screens.home

import com.arctouch.codechallenge.android.screens.base.MvvmViewModel
import com.arctouch.codechallenge.android.screens.base.ViewModelNavigator
import com.arctouch.codechallenge.android.screens.base.ViewModelState
import com.arctouch.codechallenge.domain.FetchAndStoreGenresUseCase
import com.arctouch.codechallenge.domain.FetchUpcomingMoviesUseCase
import com.arctouch.codechallenge.infra.schedulers.RxSchedulerProvider
import com.arctouch.codechallenge.utils.extensions.observeOnMainThread
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
    private val HomeModelMapper: HomeModelMapper
): MvvmViewModel() {

    init {
        fetchData()
    }

    fun retry() {
        fetchData()
    }

    private fun fetchData() {
        addDisposable(
            fetchAndStoreGenresUseCase.run()
                .doOnSubscribe { updateState(ViewModelState.Loading) }
                .subscribeOnIo(rxSchedulerProvider)
                .andThen(fetchUpcomingMoviesUseCase.fetch())
                .map { HomeModelMapper.mapList(it) }
                .observeOnMainThread(rxSchedulerProvider)
                .subscribe({
                    updateState(ViewModelState.Data(it))
                },{
                    handleError(it)
                })
        )
    }

    fun onItemClicked(item: HomeModel) {
        val target = NavigateToMovieDetail(item.id)
        updateNavigator(ViewModelNavigator.Navigate(target))
    }

}
