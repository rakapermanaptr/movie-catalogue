package com.example.moviecatalogue.ui.catalogue.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getTvShows(): LiveData<Resource<PagedList<TvShow>>> = movieRepository.getTvShows()
}