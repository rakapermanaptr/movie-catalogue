package com.example.moviecatalogue.ui.catalogue.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.utils.FakeData
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<TvShow>>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShows_completed_returnListOfTvShows() {
        val dummyTvShows = Resource.success(FakeData.getRemoteDummyTvShows())
        val tvShows = MutableLiveData<Resource<List<TvShow>>>()
        tvShows.value = dummyTvShows

        `when`(movieRepository.getTvShows()).thenReturn(tvShows)
        val tvShowsEntities = viewModel.getTvShows().value?.data
        verify(movieRepository).getTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(20, tvShowsEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}