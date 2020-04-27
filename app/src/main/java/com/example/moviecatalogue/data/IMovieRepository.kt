package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.MovieDetail
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.data.source.local.entity.TvShowDetail

interface IMovieRepository {

    fun getMovies(): LiveData<List<Movie>>

    fun getTvShows(): LiveData<List<TvShow>>

    fun getMovieDetail(movieId: Int): LiveData<Movie>

    fun getTvShowDetail(tvId: Int): LiveData<TvShow>
}