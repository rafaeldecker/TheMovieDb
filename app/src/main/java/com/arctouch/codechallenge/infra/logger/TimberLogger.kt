package com.arctouch.codechallenge.infra.logger

import com.arctouch.codechallenge.BuildConfig
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-19.
 */

class TimberLogger @Inject constructor(

): Logger {

    override fun start() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun d(tag: String, message: String?) {
        Timber.tag(tag).d(message)
    }

    override fun d(message: String?) {
        Timber.d(message)
    }

    override fun d(throwable: Throwable) {
        Timber.d(throwable.toString())
    }

    override fun e(tag: String, message: String?) {
        Timber.tag(tag).e(message)
    }

    override fun e(message: String?) {
        Timber.e(message)
    }

    override fun e(throwable: Throwable) {
        Timber.e(throwable.toString())
    }

}
