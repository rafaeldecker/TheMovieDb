package com.arctouch.codechallenge.domain

import com.arctouch.codechallenge.entities.Movie
import com.arctouch.codechallenge.entities.PagingData
import io.reactivex.Observable

/**
 * Created by Rafael Decker on 2019-04-19.
 */

interface FetchUpcomingMoviesUseCase {

    fun fetch(page: Long): Observable<PagingData<Movie>>

}
