package com.arctouch.codechallenge.android.screens

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arctouch.codechallenge.android.screens.base.ViewModelNavigator
import com.arctouch.codechallenge.android.screens.base.ViewModelState
import com.arctouch.codechallenge.android.screens.home.HomeModel
import com.arctouch.codechallenge.android.screens.home.HomeViewModel
import com.arctouch.codechallenge.android.screens.home.NavigateToMovieDetail
import com.arctouch.codechallenge.domain.FetchAndStoreGenresUseCase
import com.arctouch.codechallenge.domain.FetchUpcomingMoviesUseCase
import com.arctouch.codechallenge.entities.Genre
import com.arctouch.codechallenge.entities.Movie
import com.arctouch.codechallenge.entities.PagingData
import com.arctouch.codechallenge.infra.logger.Logger
import com.arctouch.codechallenge.infra.schedulers.TrampolineRxSchedulerProvider
import com.arctouch.codechallenge.utils.Mapper
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Times

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var fetchAndStoreGenresUseCase: FetchAndStoreGenresUseCase

    @Mock
    lateinit var fetchUpcomingMoviesUseCase: FetchUpcomingMoviesUseCase

    @Mock
    lateinit var homeModelMapper: Mapper<Movie, HomeModel>

    @Mock
    lateinit var logger: Logger

    private val schedulerProvider = TrampolineRxSchedulerProvider()

    lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `initial fetch fails and then update state to error`() {
        Mockito.`when`(fetchAndStoreGenresUseCase.run()).thenReturn(Completable.error(Throwable()))
        Mockito.`when`(fetchUpcomingMoviesUseCase.fetch(1)).thenReturn(Observable.error(Throwable()))
        viewModel = HomeViewModel(
            schedulerProvider,
            fetchAndStoreGenresUseCase,
            fetchUpcomingMoviesUseCase,
            homeModelMapper,
            logger
        )
        viewModel.state.observeForever {
            Assert.assertTrue(it is ViewModelState.Error)
        }
    }

    @Test
    fun `initial fetch update content state`() {
        Mockito.`when`(fetchAndStoreGenresUseCase.run()).thenReturn(Completable.complete())
        val mock = PagingData(
            1,
            1,
            emptyList<Movie>()
        )
        Mockito.`when`(fetchUpcomingMoviesUseCase.fetch(1)).thenReturn(Observable.just(mock))
        viewModel = HomeViewModel(
            schedulerProvider,
            fetchAndStoreGenresUseCase,
            fetchUpcomingMoviesUseCase,
            homeModelMapper,
            logger
        )
        viewModel.state.observeForever {
            Assert.assertTrue(it is ViewModelState.Data<*>)
        }
    }

    @Test
    fun `load more fetches next page`() {
        Mockito.`when`(fetchAndStoreGenresUseCase.run()).thenReturn(Completable.complete())
        val mock = PagingData(
            1,
            2,
            emptyList<Movie>()
        )
        val mock2 = PagingData(
            2,
            2,
            emptyList<Movie>()
        )
        Mockito.`when`(fetchUpcomingMoviesUseCase.fetch(1)).thenReturn(Observable.just(mock))
        Mockito.`when`(fetchUpcomingMoviesUseCase.fetch(2)).thenReturn(Observable.just(mock2))
        viewModel = HomeViewModel(
            schedulerProvider,
            fetchAndStoreGenresUseCase,
            fetchUpcomingMoviesUseCase,
            homeModelMapper,
            logger
        )
        viewModel.state.observeForever {
            Assert.assertTrue(it is ViewModelState.Data<*>)
            viewModel.onLoadMore()
            Mockito.verify(fetchUpcomingMoviesUseCase, Times(1)).fetch(2)
        }

    }

    @Test
    fun `load more not loads more when there is no more pages to be loaded`() {
        Mockito.`when`(fetchAndStoreGenresUseCase.run()).thenReturn(Completable.complete())
        val mock = PagingData(
            1,
            1,
            emptyList<Movie>()
        )
        Mockito.`when`(fetchUpcomingMoviesUseCase.fetch(1)).thenReturn(Observable.just(mock))
        viewModel = HomeViewModel(
            schedulerProvider,
            fetchAndStoreGenresUseCase,
            fetchUpcomingMoviesUseCase,
            homeModelMapper,
            logger
        )
        viewModel.state.observeForever {
            Assert.assertTrue(it is ViewModelState.Data<*>)
            viewModel.onLoadMore()
            Mockito.verify(fetchUpcomingMoviesUseCase, Times(0)).fetch(2)
        }

    }

    @Test
    fun `movie clicked updates the navigator`() {
        Mockito.`when`(fetchAndStoreGenresUseCase.run()).thenReturn(Completable.complete())
        val model = HomeModel(
                id = 1,
                    title = "Title",
                    genres = "Abc, def",
                    posterUrl = "http://www.image.com/image.jpg",
                    releaseDate = "2019-01-01"
        )
        val movie = Movie(
            id = 10,
            title = "title",
            overview = "overview",
            genres = listOf(
                Genre(
                    id = 20,
                    name = "Name"
                )
            ),
            posterPath = "posterPath",
            backdropPath = "backdropPath",
            releaseDate = "releaseDate"
        )
        val mock = PagingData(1, 1, listOf(movie))
        Mockito.`when`(fetchUpcomingMoviesUseCase.fetch(1)).thenReturn(Observable.just(mock))
        viewModel = HomeViewModel(
            schedulerProvider,
            fetchAndStoreGenresUseCase,
            fetchUpcomingMoviesUseCase,
            homeModelMapper,
            logger
        )
        viewModel.state.observeForever {
            Assert.assertTrue(it is ViewModelState.Data<*>)
            viewModel.onItemClicked(model)

            viewModel.navigator.observeForever {
                Assert.assertTrue(it is ViewModelNavigator.Navigate<*>)
                Assert.assertTrue((it as ViewModelNavigator.Navigate<*>).target is NavigateToMovieDetail)
                Assert.assertTrue((it.target as NavigateToMovieDetail).movieId == model.id)
            }
        }
    }

}
