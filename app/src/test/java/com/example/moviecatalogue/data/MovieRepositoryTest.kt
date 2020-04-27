package com.example.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.FakeData
import com.example.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.mock

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val moviesResponses = FakeData.getDummyRemoteMovies()
    private val movieDetailResponses = FakeData.getDummyRemoteMovies()[0]
    private val movieId = moviesResponses[0].id
    private val tvShowsResponses = FakeData.getDummyRemoteTvShows()
    private val tvShowDetailResponse = FakeData.getDummyRemoteTvShows()[0]
    private val tvShowId = tvShowsResponses[0].id

    @Test
    fun getMovies_completed_returnListOfMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(moviesResponses)
            null
        }.`when`(remote).getAllMovies(any())

        val moviesEntities = LiveDataTestUtil.getValue(movieRepository.getMovies())

        verify(remote).getAllMovies(any())
        assertNotNull(moviesEntities)
        assertEquals(moviesResponses.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getTvShows_completed_returnListOfTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowsResponses)
            null
        }.`when`(remote).getAllTvShows(any())

        val tvShowsEntities = LiveDataTestUtil.getValue(movieRepository.getTvShows())

        verify(remote).getAllTvShows(any())
        assertNotNull(tvShowsEntities)
        assertEquals(tvShowsResponses.size.toLong(), tvShowsEntities.size.toLong())
    }

    @Test
    fun getMovieDetail_completed_returnTheRightData() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
                .onMovieDetailReceived(movieDetailResponses)
            null
        }.`when`(remote).getMovieDetail(eq(movieId!!), any())

        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))

        verify(remote).getMovieDetail(eq(movieId), any())
        assertNotNull(movieEntity)
        assertEquals(movieDetailResponses.id, movieEntity.id)
        assertEquals(movieDetailResponses.backdropPath, movieEntity.backdropPath)
        assertEquals(movieDetailResponses.title, movieEntity.title)
        assertEquals(movieDetailResponses.overview, movieEntity.overview)
    }

    @Test
    fun getTvShowDetail() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback)
                .onTvShowDetailReceived(tvShowDetailResponse)
            null
        }.`when`(remote).getTvShowDetail(eq(tvShowId!!), any())

        val tvShowEntity = LiveDataTestUtil.getValue(movieRepository.getTvShowDetail(tvShowId))

        verify(remote).getTvShowDetail(eq(tvShowId), any())
        assertNotNull(tvShowEntity)
        assertEquals(tvShowDetailResponse.id, tvShowEntity.id)
        assertEquals(tvShowDetailResponse.backdropPath, tvShowEntity.backdropPath)
        assertEquals(tvShowDetailResponse.name, tvShowEntity.name)
        assertEquals(tvShowDetailResponse.overview, tvShowEntity.overview)
    }
}