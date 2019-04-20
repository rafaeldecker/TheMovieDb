package com.arctouch.codechallenge.domain

import io.reactivex.Completable

/**
 * Created by Rafael Decker on 2019-04-19.
 */

interface FetchAndStoreGenresUseCase {

    fun run(): Completable

}
