package com.arctouch.codechallenge.domain

import com.arctouch.codechallenge.entities.Genre
import com.arctouch.codechallenge.infra.api.ApiLocaleProvider
import com.arctouch.codechallenge.infra.api.TmdbApi
import com.arctouch.codechallenge.infra.api.models.GenreItem
import com.arctouch.codechallenge.infra.api.models.GenreResponse
import com.arctouch.codechallenge.infra.cache.GenresCache
import com.arctouch.codechallenge.infra.schedulers.TrampolineRxSchedulerProvider
import com.arctouch.codechallenge.utils.Mapper
import com.arctouch.codechallenge.utils.extensions.saveMainThread
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Times

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class FetchAndStoreGenresUseCaseTest {

    @Mock
    lateinit var api: TmdbApi

    @Mock
    lateinit var localeProvider: ApiLocaleProvider

    @Mock
    lateinit var mapper: Mapper<GenreItem, Genre>

    @Mock
    lateinit var cache: GenresCache

    private val rxSchedulerProvider = TrampolineRxSchedulerProvider()

    private lateinit var useCase: FetchAndStoreGenresUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = FetchAndStoreGenresUseCaseImpl(
            api,
            localeProvider,
            mapper,
            cache
        )
    }

    @Test
    fun `test use case is storing genres in cache`() {
        val language = "pt-BR"
        val apiResponse = GenreResponse(listOf(GenreItem(1, "Comedy")))
        val genresMapped = listOf(Genre(1, "Comedy"))
        Mockito.`when`(localeProvider.language()).thenReturn(language)
        Mockito.`when`(api.genres(API_KEY, language)).thenReturn(Observable.just(apiResponse))
        Mockito.`when`(mapper.mapList(apiResponse.genres)).thenReturn(genresMapped)
        useCase.run()
            .saveMainThread(rxSchedulerProvider)
            .subscribe({
                Mockito.verify(cache, Times(1)).genres = genresMapped
            }, {

            })
    }

    companion object {
        private const val API_KEY = "1f54bd990f1cdfb230adb312546d765d"
    }

}
