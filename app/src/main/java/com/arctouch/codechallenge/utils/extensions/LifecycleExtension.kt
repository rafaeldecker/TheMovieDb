package com.arctouch.codechallenge.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Rafael Decker on 2019-04-19.
 */

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer { it?.let(body) })

fun <L : LiveData<Throwable>> LifecycleOwner.error(liveData: L, body: (Throwable) -> Unit) =
    liveData.observe(this, Observer { it?.let(body) })
