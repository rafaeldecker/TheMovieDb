package com.arctouch.codechallenge.injection

import com.arctouch.codechallenge.infra.api.ApiLocaleProvider
import com.arctouch.codechallenge.infra.api.ApiLocaleProviderImpl
import com.arctouch.codechallenge.infra.api.RetrofitFactory
import com.arctouch.codechallenge.infra.api.TmdbApi
import com.arctouch.codechallenge.infra.logger.Logger
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Rafael Decker on 2019-04-19.
 */

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logger: Logger
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        val httpLogger = HttpLoggingInterceptor.Logger { logger.d("OkHttp", it) }
        val logging = HttpLoggingInterceptor(httpLogger)
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.interceptors().add(logging)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofitFactory: RetrofitFactory
    ): Retrofit = retrofitFactory.create()

    @Provides
    fun provideApiLocaleProvider(
        localeProvider: ApiLocaleProviderImpl
    ): ApiLocaleProvider = localeProvider

    @Provides
    @Singleton
    fun provideTmdbApi(
        retrofit: Retrofit
    ): TmdbApi = retrofit.create(TmdbApi::class.java)

}
