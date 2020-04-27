package com.example.moviecatalogue.data.source.remote.network

import com.example.moviecatalogue.data.source.local.entity.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing")
    fun getMovies(): Deferred<ResultResponse<Movie>>

    @GET("tv/airing_today")
    fun getTvShows(): Deferred<ResultResponse<TvShow>>

    @GET("movie/{movie_id}")
    fun getMovieDetail(): Deferred<MovieDetail>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(): Deferred<TvShowDetail>
}