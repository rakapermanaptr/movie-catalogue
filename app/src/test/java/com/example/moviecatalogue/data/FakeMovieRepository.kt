package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.MovieDetail
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.data.source.local.entity.TvShowDetail
import com.example.moviecatalogue.data.source.remote.RemoteDataSource

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) :
    IMovieRepository {

    companion object {
        @Volatile
        private var instance: FakeMovieRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): FakeMovieRepository =
            instance ?: synchronized(this) {
                instance ?: FakeMovieRepository(remoteDataSource)
            }
    }

    override fun getMovies(): LiveData<List<Movie>> {
        val movies = MutableLiveData<List<Movie>>()

        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponse: List<Movie>) {
                movies.postValue(moviesResponse)
            }
        })

        return movies
    }

    override fun getTvShows(): LiveData<List<TvShow>> {
        val tvShows = MutableLiveData<List<TvShow>>()

        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowsResponse: List<TvShow>) {
                tvShows.postValue(tvShowsResponse)
            }
        })

        return tvShows
    }

    override fun getMovieDetail(movieId: Int): LiveData<Movie> {
        val movie = MutableLiveData<Movie>()

        remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback {
            override fun onMovieDetailReceived(movieResponse: Movie) {
                movie.postValue(movieResponse)
            }
        })

        return movie
    }

    override fun getTvShowDetail(tvId: Int): LiveData<TvShow> {
        val tvShow = MutableLiveData<TvShow>()

        remoteDataSource.getTvShowDetail(tvId, object : RemoteDataSource.LoadTvShowDetailCallback {
            override fun onTvShowDetailReceived(tvShowResponse: TvShow) {
                tvShow.postValue(tvShowResponse)
            }
        })

        return tvShow
    }

}