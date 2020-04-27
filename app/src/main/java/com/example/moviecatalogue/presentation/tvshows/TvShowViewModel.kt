package com.example.moviecatalogue.presentation.tvshows

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {

    fun getTvShows(): List<TvShow> = DataDummy.generateTvShows()
}