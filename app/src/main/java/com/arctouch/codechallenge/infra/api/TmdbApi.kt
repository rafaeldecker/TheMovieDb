package com.arctouch.codechallenge.infra.api

import com.arctouch.codechallenge.infra.api.models.GenreResponse
import com.arctouch.codechallenge.infra.api.models.MovieItem
import com.arctouch.codechallenge.infra.api.models.UpcomingMovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("genre/movie/list")
    fun genres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Observable<GenreResponse>

    @GET("movie/upcoming")
    fun upcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Long,
        @Query("region") region: String
    ): Observable<UpcomingMovieResponse>

    @GET("movie/{id}")
    fun movie(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Observable<MovieItem>
}
