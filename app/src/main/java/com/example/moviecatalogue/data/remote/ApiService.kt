package com.example.moviecatalogue.data.remote

import com.example.moviecatalogue.domain.entity.Movie
import com.example.moviecatalogue.domain.entity.MovieDetail
import com.example.moviecatalogue.domain.entity.ResultResponse
import com.example.moviecatalogue.domain.entity.TvShow
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/now_playing")
    fun getMovies(): Deferred<ResultResponse<Movie>>

    @GET("tv/airing_today")
    fun getTvShows(): Deferred<ResultResponse<TvShow>>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") id: Int): Deferred<MovieDetail>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(@Path("tv_id") id: Int): Deferred<TvShow>
}