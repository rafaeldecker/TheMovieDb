package com.arctouch.codechallenge.infra.api.mappers

import com.arctouch.codechallenge.entities.Genre
import com.arctouch.codechallenge.infra.api.GenreItem
import com.arctouch.codechallenge.utils.Mapper
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-19.
 */

class GenreMapper @Inject constructor(

): Mapper<GenreItem, Genre> {

    override fun map(param: GenreItem): Genre =
        Genre(
            param.id,
            param.name
        )

    override fun mapReverse(param: Genre): GenreItem =
        GenreItem(
            param.id,
            param.name
        )
}
