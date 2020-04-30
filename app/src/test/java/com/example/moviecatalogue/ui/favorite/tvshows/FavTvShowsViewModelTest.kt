package com.example.moviecatalogue.ui.favorite.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.TvShow
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
class FavTvShowsViewModelTest {

    private lateinit var viewModel: FavTvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShow>>

    @Mock
    private lateinit var pagedList: PagedList<TvShow>

    @Before
    fun setUp() {
        viewModel = FavTvShowsViewModel(movieRepository)
    }

    @Test
    fun getFavoriteTvShows_completed_returnFavoriteTvShows() {
        val dummyTvShows = pagedList
        `when`(dummyTvShows.size).thenReturn(10)
        val tvShows = MutableLiveData<PagedList<TvShow>>()
        tvShows.value = dummyTvShows

        `when`(movieRepository.getFavoriteTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getFavoriteTvShows().value
        verify(movieRepository).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.size)

        viewModel.getFavoriteTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}