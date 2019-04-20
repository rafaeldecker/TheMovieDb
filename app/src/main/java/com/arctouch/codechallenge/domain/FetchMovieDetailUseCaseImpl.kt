package com.arctouch.codechallenge.domain

import com.arctouch.codechallenge.BuildConfig
import com.arctouch.codechallenge.entities.Movie
import com.arctouch.codechallenge.infra.api.ApiLocaleProvider
import com.arctouch.codechallenge.infra.api.TmdbApi
import com.arctouch.codechallenge.infra.api.mappers.MovieMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class FetchMovieDetailUseCaseImpl @Inject constructor(
    private val api: TmdbApi,
    private val apiLocaleProvider: ApiLocaleProvider,
    private val movieMapper: MovieMapper
): FetchMovieDetailUseCase {

    override fun fetch(movieId: Long): Observable<Movie> =
        api.movie(movieId, BuildConfig.API_KEY, apiLocaleProvider.language())
            .map { movieMapper.map(it) }

}
