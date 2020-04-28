package com.example.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.data.source.remote.network.ApiService
import com.example.moviecatalogue.utils.EspressoIdlingResource
import com.example.moviecatalogue.utils.Result
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

    fun getAllMovies():  LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()

        val resultMovies = MutableLiveData<ApiResponse<List<Movie>>>()
        coroutineScope.launch {
            val moviesDeferred = service.getMovies()
            try {
                val moviesResult = moviesDeferred.await()
                resultMovies.value = ApiResponse.success(moviesResult.results)
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                Log.e("RemoteDataSource","getAllMovies Error :  ${e.localizedMessage}")
            }
        }

        return resultMovies
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()

        val resultTvShows = MutableLiveData<ApiResponse<List<TvShow>>>()
        coroutineScope.launch {
            val tvShowDeferred = service.getTvShows()
            try {
                val tvShowResult = tvShowDeferred.await()
                resultTvShows.value = ApiResponse.success(tvShowResult.results)
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                Log.e("RemoteDataSource","getAllTvShows Error :  ${e.localizedMessage}")
            }
        }

        return resultTvShows
    }

    fun getMovieDetail(movieId: Int): LiveData<ApiResponse<Movie>> {
        EspressoIdlingResource.increment()

        val movieDetail = MutableLiveData<ApiResponse<Movie>>()
        coroutineScope.launch {
            val movieDeferred = service.getMovieDetail(movieId)
            try {
                EspressoIdlingResource.decrement()
                val movieResult = movieDeferred.await()
                movieDetail.value = ApiResponse.success(movieResult)
            } catch (e: Exception) {
                Log.e("RemoteDataSource","getMovieDetail Error :  ${e.localizedMessage}")
            }
        }

        return movieDetail
    }

    fun getTvShowDetail(tvId: Int): LiveData<ApiResponse<TvShow>> {
        EspressoIdlingResource.increment()

        val tvShowDetail = MutableLiveData<ApiResponse<TvShow>>()
        coroutineScope.launch {
            val tvShowDeferred = service.getTvShowDetail(tvId)
            try {
                EspressoIdlingResource.decrement()
                val tvShowResult = tvShowDeferred.await()
                tvShowDetail.value = ApiResponse.success(tvShowResult)
            } catch (e: Exception) {
                Log.e("RemoteDataSource","getTvShowDetail Error :  ${e.localizedMessage}")
            }
        }

        return tvShowDetail
    }

}