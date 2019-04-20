package com.arctouch.codechallenge.infra.cache

import com.arctouch.codechallenge.infra.api.GenreItem

object Cache {

    var genres = listOf<GenreItem>()

    fun cacheGenres(genres: List<GenreItem>) {
        Cache.genres = genres
    }
}
