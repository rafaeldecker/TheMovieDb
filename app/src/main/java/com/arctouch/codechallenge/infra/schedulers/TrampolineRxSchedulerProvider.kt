package com.arctouch.codechallenge.infra.schedulers

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class TrampolineRxSchedulerProvider: RxSchedulerProvider {

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()

}
