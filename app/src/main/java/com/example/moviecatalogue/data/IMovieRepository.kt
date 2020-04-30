package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.vo.Resource

interface IMovieRepository {

    fun getMovies(): LiveData<Resource<PagedList<Movie>>>

    fun getTvShows(): LiveData<Resource<PagedList<TvShow>>>

    fun getMovieDetail(movieId: Int): LiveData<Resource<Movie>>

    fun getTvShowDetail(tvId: Int): LiveData<Resource<TvShow>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)

    fun getFavoriteMovies(): LiveData<PagedList<Movie>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShow>>
}