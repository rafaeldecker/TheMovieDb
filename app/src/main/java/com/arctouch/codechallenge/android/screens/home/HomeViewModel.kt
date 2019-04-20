package com.arctouch.codechallenge.android.screens.home

import androidx.lifecycle.ViewModel
import com.arctouch.codechallenge.android.screens.base.MvvmViewModel
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

class HomeViewModel @Inject constructor(
    private val rxSchedulerProvider: RxSchedulerProvider,
    private val fetchAndStoreGenresUseCase: FetchAndStoreGenresUseCase,
    private val fetchUpcomingMoviesUseCase: FetchUpcomingMoviesUseCase,
    private val movieModelMapper: MovieModelMapper
): MvvmViewModel() {

    init {
        fetchData()
    }

    private fun fetchData() {
        addDisposable(
            fetchAndStoreGenresUseCase.run()
                .doOnSubscribe { updateState(ViewModelState.Loading) }
                .subscribeOnIo(rxSchedulerProvider)
                .andThen(fetchUpcomingMoviesUseCase.fetch())
                .map { movieModelMapper.mapList(it) }
                .observeOnMainThread(rxSchedulerProvider)
                .subscribe({
                    updateState(ViewModelState.Data(it))
                },{
                    handleError(it)
                })
        )
    }

}
