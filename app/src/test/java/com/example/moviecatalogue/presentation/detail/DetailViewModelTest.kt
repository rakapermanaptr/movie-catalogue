package com.example.moviecatalogue.presentation.detail

import com.example.moviecatalogue.utils.DataDummy
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    private val dummyTvShow = DataDummy.generateTvShows()[0]
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()

        viewModel.setSelectedMovie(movieId!!)
        viewModel.setSelectedTvShow(tvShowId!!)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie.id!!)
        val movie = viewModel.getMovie()

        assertNotNull(movie)
        assertEquals(dummyMovie.id, movie.id)
        assertEquals(dummyMovie.backdropPath, movie.backdropPath)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.overview, movie.overview)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedTvShow(dummyTvShow.id!!)
        val tvShow = viewModel.getTvShow()

        assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, tvShow.id)
        assertEquals(dummyTvShow.backdropPath, tvShow.backdropPath)
        assertEquals(dummyTvShow.name, tvShow.name)
        assertEquals(dummyTvShow.overview, tvShow.overview)
    }
}