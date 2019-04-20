package com.arctouch.codechallenge.domain

import com.arctouch.codechallenge.entities.Movie
import io.reactivex.Observable

/**
 * Created by Rafael Decker on 2019-04-20.
 */

interface FetchMovieDetailUseCase {

    fun fetch(movieId: Long): Observable<Movie>

}