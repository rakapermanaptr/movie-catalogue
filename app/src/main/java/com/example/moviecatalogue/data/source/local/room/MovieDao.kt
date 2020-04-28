package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow

@Dao
interface MovieDao {

    @Query("SELECT * FROM moviesentities")
    fun getMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM tvshowentities")
    fun getTvShows(): LiveData<List<TvShow>>

    @Query("SELECT * FROM moviesentities WHERE id = :id")
    fun getMovieDetail(id: Int): LiveData<Movie>

    @Query("SELECT * FROM tvshowentities WHERE id = :id")
    fun getTvShowDetail(id: Int): LiveData<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShow>)

    @Update
    fun updateMovie(movie: Movie)

    @Update
    fun updateTvShow(tvShow: TvShow)

    @Query("SELECT * FROM moviesentities WHERE isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM tvshowentities WHERE isFavorite = 1")
    fun getFavoriteTvShows(): LiveData<List<TvShow>>

}