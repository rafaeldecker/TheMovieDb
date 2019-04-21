package com.arctouch.codechallenge.infra.api.models

import com.squareup.moshi.Json

/**
 * Created by Rafael Decker on 2019-04-20.
 */

abstract class ApiPagedData<T>(
    val page: Long,
    @Json(name = "total_pages") val totalPages: Long,
    val results: List<T>
)