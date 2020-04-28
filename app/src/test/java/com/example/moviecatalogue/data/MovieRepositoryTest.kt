package com.example.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.FakeData
import com.example.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val moviesResponses = FakeData.getRemoteDummyMovies()
    private val movieDetailResponses = FakeData.getRemoteDummyMovies()[0]
    private val movieId = moviesResponses[0].id
    private val tvShowsResponses = FakeData.getRemoteDummyTvShows()
    private val tvShowDetailResponse = FakeData.getRemoteDummyTvShows()[0]
    private val tvShowId = tvShowsResponses[0].id

    @Test
    fun getMovies_completed_returnListOfMovies() {
        val dummyMovies = MutableLiveData<List<Movie>>()
        dummyMovies.value = FakeData.getRemoteDummyMovies()

        `when`(local.getAllMovies()).thenReturn(dummyMovies)
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getMovies())
        verify(local).getAllMovies()

        assertNotNull(movieEntities.data)
        assertEquals(moviesResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShows_completed_returnListOfTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShow>>()
        dummyTvShows.value = FakeData.getRemoteDummyTvShows()

        `when`(local.getAllTvShows()).thenReturn(dummyTvShows)
        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getTvShows())
        verify(local).getAllTvShows()

        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowsResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail_completed_returnTheRightData() {
        val dummyMovie = MutableLiveData<Movie>()
        dummyMovie.value = FakeData.getRemoteDummyMovie(movieId!!)

        `when`(local.getMovieDetail(movieId)).thenReturn(dummyMovie)
        val movie = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))
        verify(local).getMovieDetail(movieId)

        assertNotNull(movie)
        assertEquals(movieDetailResponses, movie.data)
    }

    @Test
    fun getTvShowDetail_completed_returnTvShowDetail() {
        val dummyTvShow = MutableLiveData<TvShow>()
        dummyTvShow.value = FakeData.getRemoteDummyTvShow(tvShowId!!)

        `when`(local.getTvShowDetail(tvShowId)).thenReturn(dummyTvShow)
        val tvShow = LiveDataTestUtil.getValue(movieRepository.getTvShowDetail(tvShowId))
        verify(local).getTvShowDetail(tvShowId)

        assertNotNull(tvShow)
        assertEquals(tvShowDetailResponse, tvShow.data)
    }

    @Test
    fun getFavoriteMovies_completed_returnFavoriteMovies() {
        val dummyMovies = MutableLiveData<List<Movie>>()
        dummyMovies.value = FakeData.getRemoteDummyMovies()

        `when`(local.getFavoriteMovies()).thenReturn(dummyMovies)
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getFavoriteMovies())
        verify(local).getFavoriteMovies()

        assertNotNull(movieEntities)
        assertEquals(moviesResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getFavoriteTvShows_completed_returnFavoriteTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShow>>()
        dummyTvShows.value = FakeData.getRemoteDummyTvShows()

        `when`(local.getFavoriteTvShows()).thenReturn(dummyTvShows)
        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getFavoriteTvShows())
        verify(local).getFavoriteTvShows()

        assertNotNull(tvShowEntities)
        assertEquals(tvShowsResponses.size.toLong(), tvShowEntities.size.toLong())
    }
}