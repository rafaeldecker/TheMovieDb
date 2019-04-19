package com.arctouch.codechallenge.infra.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-19.
 */

class RetrofitFactory @Inject constructor(
    private val okHttpClient: OkHttpClient
) {

    fun create(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TmdbApi.URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}
