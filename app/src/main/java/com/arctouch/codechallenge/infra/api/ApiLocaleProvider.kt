package com.arctouch.codechallenge.infra.api

/**
 * Created by Rafael Decker on 2019-04-19.
 */

interface ApiLocaleProvider {

    fun language(): String
    fun region(): String

}
