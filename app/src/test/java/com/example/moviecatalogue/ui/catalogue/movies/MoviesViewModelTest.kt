package com.example.moviecatalogue.ui.catalogue.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.utils.FakeData
import com.example.moviecatalogue.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var movieRepository = Mockito.mock(MovieRepository::class.java)

    @Mock
    private lateinit var observer: Observer<Resource<List<Movie>>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesViewModel(movieRepository)
    }

    @Test
    fun getMovies_completed_returnListOfMovies() {
        val dummyMovies = Resource.success(FakeData.getRemoteDummyMovies())
        val movies = MutableLiveData<Resource<List<Movie>>>()
        movies.value = dummyMovies

        `when`(movieRepository.getMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getMovies().value?.data
        verify(movieRepository).getMovies()
        assertNotNull(moviesEntities)
        assertEquals(20, moviesEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}