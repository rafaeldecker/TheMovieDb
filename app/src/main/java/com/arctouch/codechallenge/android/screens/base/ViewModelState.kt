package com.arctouch.codechallenge.android.screens.base

/**
 * Created by Rafael Decker on 2019-04-19.
 */

sealed class ViewModelState {

    data class Data<T>(val data: T) : ViewModelState()
    data class Error(val throwable: Throwable) : ViewModelState()
    object Loading : ViewModelState()

}
