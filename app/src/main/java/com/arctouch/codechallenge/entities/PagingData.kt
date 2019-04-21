package com.arctouch.codechallenge.entities

/**
 * Created by Rafael Decker on 2019-04-20.
 */

data class PagingData<T>(
    val page: Long,
    val totalPages: Long,
    val data: List<T>
)