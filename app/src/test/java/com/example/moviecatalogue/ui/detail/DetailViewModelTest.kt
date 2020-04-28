package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.utils.FakeData
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = FakeData.getRemoteDummyMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyTvShow = FakeData.getRemoteDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<Movie>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShow>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)

        viewModel.setSelectedMovie(movieId!!)
        viewModel.setSelectedTvShow(tvShowId!!)
    }

    @Test
    fun getMovieDetail_completed_returnTheRightData() {
        val dummyMovie = Resource.success(FakeData.getRemoteDummyMovie(movieId!!))
        val movie = MutableLiveData<Resource<Movie>>()
        movie.value = dummyMovie

        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(movie)

        viewModel.movie.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail_completed_returnTheRightData() {
        val dummyTvShow = Resource.success(FakeData.getRemoteDummyTvShow(tvShowId!!))
        val tvShow = MutableLiveData<Resource<TvShow>>()
        tvShow.value = dummyTvShow

        `when`(movieRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)

        viewModel.tvShow.observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}
