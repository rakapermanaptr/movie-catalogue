package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.vo.Resource

interface IMovieRepository {

    fun getMovies(): LiveData<Resource<List<Movie>>>

    fun getTvShows(): LiveData<Resource<List<TvShow>>>

    fun getMovieDetail(movieId: Int): LiveData<Resource<Movie>>

    fun getTvShowDetail(tvId: Int): LiveData<Resource<TvShow>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)

    fun getFavoriteMovies(): LiveData<List<Movie>>

    fun getFavoriteTvShows(): LiveData<List<TvShow>>
}