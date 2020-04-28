package com.example.moviecatalogue.ui.catalogue.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.vo.Resource

class MoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Movie>>> = movieRepository.getMovies()
}