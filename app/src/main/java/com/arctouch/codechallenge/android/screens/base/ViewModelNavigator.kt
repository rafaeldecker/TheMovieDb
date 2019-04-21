package com.arctouch.codechallenge.android.screens.base

/**
 * Created by Rafael Decker on 2019-04-19.
 */

sealed class ViewModelNavigator {

    data class Navigate<T>(val target: T): ViewModelNavigator()

}

fun withNavigator(navigator: Any, block: (Any) -> (Unit)) {
    if (navigator is ViewModelNavigator.Navigate<*> && navigator.target != null) {
        block(navigator.target)
    }
}