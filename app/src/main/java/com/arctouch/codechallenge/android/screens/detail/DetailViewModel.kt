package com.arctouch.codechallenge.android.screens.detail

import com.arctouch.codechallenge.android.screens.base.MvvmViewModel
import com.arctouch.codechallenge.android.screens.base.ViewModelState
import com.arctouch.codechallenge.domain.FetchMovieDetailUseCase
import com.arctouch.codechallenge.infra.schedulers.RxSchedulerProvider
import com.arctouch.codechallenge.utils.extensions.observeOnMainThread
import com.arctouch.codechallenge.utils.extensions.subscribeOnIo
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class DetailViewModel @Inject constructor(
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase,
    private val rxSchedulerProvider: RxSchedulerProvider,
    private val mapper: DetailModelMapper
): MvvmViewModel() {

    fun run(movieId: Long) {
        addDisposable(
            fetchMovieDetailUseCase.fetch(movieId)
                .doOnSubscribe { updateState(ViewModelState.Loading) }
                .subscribeOnIo(rxSchedulerProvider)
                .map { mapper.map(it) }
                .observeOnMainThread(rxSchedulerProvider)
                .subscribe({
                    updateState(ViewModelState.Data(it))
                },{
                    handleError(it)
                })
        )
    }

}