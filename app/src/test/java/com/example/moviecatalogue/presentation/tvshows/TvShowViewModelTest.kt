package com.example.moviecatalogue.presentation.tvshows

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShows = viewModel.getTvShows()
        assertNotNull(tvShows)
        assertEquals(10, tvShows.size)
    }
}