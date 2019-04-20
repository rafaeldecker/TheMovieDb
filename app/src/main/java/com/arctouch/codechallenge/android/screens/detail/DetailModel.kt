package com.arctouch.codechallenge.android.screens.detail

/**
 * Created by Rafael Decker on 2019-04-20.
 */

data class DetailModel(
    val backdropImageUrl: String?,
    val items: List<DetailModelItem>
)

sealed class DetailModelItem {

    data class Title(val text: String): DetailModelItem()
    data class Overview(val imageUrl: String?, val overview: String?): DetailModelItem()
    data class Genres(val genres: String): DetailModelItem()
    data class ReleaseDate(val releaseDate: String): DetailModelItem()

}

