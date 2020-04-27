package com.example.moviecatalogue.presentation.movies

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.utils.DataDummy

class MoviesViewModel : ViewModel() {

    fun getMovies(): List<Movie> = DataDummy.generateDummyMovies()
}