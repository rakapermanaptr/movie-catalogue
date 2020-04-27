package com.example.moviecatalogue.presentation.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.utils.DataDummy

class TvShowViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getTvShows(): LiveData<List<TvShow>> = movieRepository.getTvShows()
}