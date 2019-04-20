package com.arctouch.codechallenge.android.screens.home

/**
 * Created by Rafael Decker on 2019-04-20.
 */

data class HomeModel(
    val id: Long,
    val title: String,
    val genres: String,
    val posterUrl: String?,
    val releaseDate: String
)