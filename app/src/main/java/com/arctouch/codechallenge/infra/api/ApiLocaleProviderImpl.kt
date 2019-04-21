package com.arctouch.codechallenge.infra.api

import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-19.
 */

class ApiLocaleProviderImpl @Inject constructor(

): ApiLocaleProvider {

    override fun language(): String = DEFAULT_LANGUAGE

    override fun region(): String = DEFAULT_REGION

    companion object {
        private const val DEFAULT_LANGUAGE = "pt-BR"
        private const val DEFAULT_REGION = ""
    }

}
