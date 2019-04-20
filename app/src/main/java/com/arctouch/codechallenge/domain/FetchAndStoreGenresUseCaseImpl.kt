package com.arctouch.codechallenge.domain

import com.arctouch.codechallenge.BuildConfig
import com.arctouch.codechallenge.infra.api.ApiLocaleProvider
import com.arctouch.codechallenge.infra.api.TmdbApi
import com.arctouch.codechallenge.infra.api.mappers.GenreMapper
import com.arctouch.codechallenge.infra.cache.GenresCache
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Rafael Decker on 2019-04-19.
 */

class FetchAndStoreGenresUseCaseImpl @Inject constructor(
    private val api: TmdbApi,
    private val localeProvider: ApiLocaleProvider,
    private val mapper: GenreMapper,
    private val cache: GenresCache
) : FetchAndStoreGenresUseCase {

    override fun run(): Completable =
        api.genres(BuildConfig.API_KEY, localeProvider.language())
            .flatMapCompletable {
                cache.genres = mapper.mapList(it.genres)
                Completable.complete()
            }

}
