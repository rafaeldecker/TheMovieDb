package com.arctouch.codechallenge.utils.extensions

import com.arctouch.codechallenge.infra.schedulers.RxSchedulerProvider
import io.reactivex.Observable

/**
 * Created by Rafael Decker on 2019-04-19.
 */

fun <R> Observable<R>.subscribeOnIo(
    rxSchedulerProvider: RxSchedulerProvider
): Observable<R> = subscribeOn(rxSchedulerProvider.io())

fun <R> Observable<R>.observeOnMainThread(
    rxSchedulerProvider: RxSchedulerProvider
): Observable<R> = observeOn(rxSchedulerProvider.ui())

fun <R> Observable<R>.observeOnIo(
    rxSchedulerProvider: RxSchedulerProvider
): Observable<R> = observeOn(rxSchedulerProvider.io())

fun <R> Observable<R>.saveMainThread(
    rxSchedulerProvider: RxSchedulerProvider
): Observable<R> = subscribeOnIo(rxSchedulerProvider).observeOnMainThread(rxSchedulerProvider)