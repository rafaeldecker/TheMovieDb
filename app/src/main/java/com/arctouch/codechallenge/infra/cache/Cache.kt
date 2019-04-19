package com.arctouch.codechallenge.infra.cache

import com.arctouch.codechallenge.infra.api.Genre

object Cache {

    var genres = listOf<Genre>()

    fun cacheGenres(genres: List<Genre>) {
        Cache.genres = genres
    }
}
