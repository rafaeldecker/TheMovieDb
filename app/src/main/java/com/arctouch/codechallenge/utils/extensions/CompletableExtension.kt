package com.arctouch.codechallenge.utils.extensions

import com.arctouch.codechallenge.infra.schedulers.RxSchedulerProvider
import io.reactivex.Completable

/**
 * Created by Rafael Decker on 2019-04-19.
 */

fun Completable.subscribeOnIo(
    rxSchedulerProvider: RxSchedulerProvider
): Completable = subscribeOn(rxSchedulerProvider.io())

fun Completable.observeOnMainThread(
    rxSchedulerProvider: RxSchedulerProvider
): Completable = observeOn(rxSchedulerProvider.ui())

fun Completable.saveMainThread(
    rxSchedulerProvider: RxSchedulerProvider
): Completable = subscribeOnIo(rxSchedulerProvider)
    .observeOnMainThread(rxSchedulerProvider)