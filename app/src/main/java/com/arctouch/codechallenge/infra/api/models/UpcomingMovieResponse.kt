package com.arctouch.codechallenge.infra.api.models

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class UpcomingMovieResponse(
    page: Long,
    totalPages: Long,
    results: List<MovieItem>
) : ApiPagedData<MovieItem>(page, totalPages, results)