package com.example.moviecatalogue.ui.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.utils.FakeData
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavMoviesViewModelTest {

    private lateinit var viewModel: FavMoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<Movie>>

    @Mock
    private lateinit var pagedList: PagedList<Movie>

    @Before
    fun setUp() {
        viewModel = FavMoviesViewModel(movieRepository)
    }

    @Test
    fun getFavoriteMovies_completed_returnFavoriteMovies() {
        val dummyMovie = pagedList
        `when`(dummyMovie.size).thenReturn(10)
        val movies = MutableLiveData<PagedList<Movie>>()
        movies.value = dummyMovie

        `when`(movieRepository.getFavoriteMovies()).thenReturn(movies)
        val movieEntities = viewModel.getFavoriteMovies().value
        verify(movieRepository).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}