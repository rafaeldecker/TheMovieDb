package com.arctouch.codechallenge.infra.schedulers

import io.reactivex.Scheduler

/**
 * Created by Rafael Decker on 2019-04-19.
 */

interface RxSchedulerProvider {

    fun io(): Scheduler

    fun computation(): Scheduler

    fun ui(): Scheduler

}
