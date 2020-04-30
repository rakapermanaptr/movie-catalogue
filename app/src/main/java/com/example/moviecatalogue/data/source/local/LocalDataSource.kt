package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, Movie> = movieDao.getMovies()

    fun getMovieDetail(id: Int): LiveData<Movie> = movieDao.getMovieDetail(id)

    fun getAllTvShows(): DataSource.Factory<Int, TvShow> = movieDao.getTvShows()

    fun getTvShowDetail(id: Int): LiveData<TvShow> = movieDao.getTvShowDetail(id)

    fun insertMovies(movies: List<Movie>) = movieDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShow>) = movieDao.insertTvShows(tvShows)

    fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShow, newState: Boolean) {
        tvShow.isFavorite = newState
        movieDao.updateTvShow(tvShow)
    }

    fun updateMovieDetail(movie: Movie) {
        movieDao.updateMovie(movie)
    }

    fun updateTvShowDetail(tvShow: TvShow) {
        movieDao.updateTvShow(tvShow)
    }

    fun getFavoriteMovies(): DataSource.Factory<Int, Movie> = movieDao.getFavoriteMovies()

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShow> = movieDao.getFavoriteTvShows()
}