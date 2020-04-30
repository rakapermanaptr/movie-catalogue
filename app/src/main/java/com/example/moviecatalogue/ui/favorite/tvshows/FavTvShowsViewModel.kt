package com.example.moviecatalogue.ui.favorite.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.TvShow

class FavTvShowsViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getFavoriteTvShows(): LiveData<PagedList<TvShow>> = movieRepository.getFavoriteTvShows()
}