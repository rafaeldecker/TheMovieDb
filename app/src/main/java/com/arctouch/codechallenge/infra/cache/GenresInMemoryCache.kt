package com.arctouch.codechallenge.infra.cache

import com.arctouch.codechallenge.entities.Genre

/**
 * Created by Rafael Decker on 2019-04-19.
 */

object GenresInMemoryCache : GenresCache {

    private var _genres = listOf<Genre>()

    override var genres: List<Genre>
        get() = _genres
        set(value) {
            _genres = value
        }

}
