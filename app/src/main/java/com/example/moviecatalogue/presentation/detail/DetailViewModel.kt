package com.example.moviecatalogue.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.MovieDetail
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.data.source.local.entity.TvShowDetail
import com.example.moviecatalogue.utils.DataDummy

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var movieId: Int = 0

    private var tvShowId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): LiveData<Movie> = movieRepository.getMovieDetail(movieId)

    fun getTvShow(): LiveData<TvShow> = movieRepository.getTvShowDetail(tvShowId)

}