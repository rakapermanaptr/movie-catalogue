package com.example.moviecatalogue.data.source.remote

import android.util.Log
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.data.source.remote.network.ApiService
import com.example.moviecatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RemoteDataSource(private val service: ApiService) {

    private val job = Job()

    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        coroutineScope.launch {
            val moviesDeferred = service.getMovies()
            try {
                val moviesResult = moviesDeferred.await()
                callback.onAllMoviesReceived(moviesResult.results)
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                Log.e("RemoteDataSource","getAllMovies Error :  ${e.localizedMessage}")
            }
        }
    }

    fun getAllTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        coroutineScope.launch {
            val tvShowDeferred = service.getTvShows()
            try {
                val tvShowResult = tvShowDeferred.await()
                callback.onAllTvShowsReceived(tvShowResult.results)
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                Log.e("RemoteDataSource","getAllTvShows Error :  ${e.localizedMessage}")
            }
        }
    }

    fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        coroutineScope.launch {
            val movieDeferred = service.getMovieDetail(movieId)
            try {
                val movieResult = movieDeferred.await()
                callback.onMovieDetailReceived(movieResult)
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                Log.e("RemoteDataSource","getMovieDetail Error :  ${e.localizedMessage}")
            }
        }
    }

    fun getTvShowDetail(tvId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        coroutineScope.launch {
            val tvShowDeferred = service.getTvShowDetail(tvId)
            try {
                val tvShowResult = tvShowDeferred.await()
                callback.onTvShowDetailReceived(tvShowResult)
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                Log.e("RemoteDataSource","getTvShowDetail Error :  ${e.localizedMessage}")
            }
        }
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesResponse: List<Movie>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowsResponse: List<TvShow>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: Movie)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowResponse: TvShow)
    }
}