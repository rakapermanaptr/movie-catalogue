package com.example.moviecatalogue.ui.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.Movie

class FavMoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavoriteMovies(): LiveData<List<Movie>> = movieRepository.getFavoriteMovies()
}