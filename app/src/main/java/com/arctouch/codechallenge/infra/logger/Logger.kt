package com.arctouch.codechallenge.infra.logger

/**
 * Created by Rafael Decker on 2019-04-19.
 */

interface Logger {

    fun start()

    fun d(tag: String, message: String?)
    fun d(message: String?)
    fun d(throwable: Throwable)

    fun e(tag: String, message: String?)
    fun e(message: String?)
    fun e(throwable: Throwable)

}
