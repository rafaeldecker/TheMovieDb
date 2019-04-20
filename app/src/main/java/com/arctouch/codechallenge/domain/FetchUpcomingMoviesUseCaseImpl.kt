package com.arctouch.codechallenge.domain

import com.arctouch.codechallenge.entities.Movie
import com.arctouch.codechallenge.infra.api.ApiLocaleProvider
import com.arctouch.codechallenge.infra.api.TmdbApi
import com.arctouch.codechallenge.infra.api.mappers.MovieMapper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class FetchUpcomingMoviesUseCaseImpl @Inject constructor(
    private val api: TmdbApi,
    private val apiLocaleProvider: ApiLocaleProvider,
    private val mapper: MovieMapper
): FetchUpcomingMoviesUseCase {

    override fun fetch(): Observable<List<Movie>> =
        api.upcomingMovies(
            TmdbApi.API_KEY,
            apiLocaleProvider.language(),
            1,
            apiLocaleProvider.region()
        ).map { mapper.mapList(it.results) }

}
