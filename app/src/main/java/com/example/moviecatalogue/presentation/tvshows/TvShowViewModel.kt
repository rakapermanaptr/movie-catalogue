package com.example.moviecatalogue.presentation.tvshows

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.domain.entity.TvShow
import com.example.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {

    fun getTvShows(): List<TvShow> = DataDummy.generateTvShows()
}