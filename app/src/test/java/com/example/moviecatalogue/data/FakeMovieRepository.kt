package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.vo.Resource

class FakeMovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors) :
    IMovieRepository {

    override fun getMovies(): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> =
                localDataSource.getAllMovies()

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<Movie>()
                for (response in data) {
                    val movie = Movie(response.id,
                        response.posterPath,
                        response.title,
                        response.backdropPath,
                        response.overview)

                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }


    override fun getTvShows(): LiveData<Resource<List<TvShow>>> {
        return object : NetworkBoundResource<List<TvShow>, List<TvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShow>> =
                localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                remoteDataSource.getAllTvShows()

            override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShow>()
                for (response in data) {
                    val tvShow = TvShow(response.id,
                        response.name,
                        response.posterPath,
                        response.backdropPath,
                        response.overview)

                    tvShowList.add(tvShow)
                }

                localDataSource.insertTvShows(tvShowList)
            }

        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, Movie>(appExecutors) {
            override fun loadFromDB(): LiveData<Movie> =
                localDataSource.getMovieDetail(movieId)

            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<Movie>> =
                remoteDataSource.getMovieDetail(movieId)

            override fun saveCallResult(data: Movie) {
                val movie = Movie(data.id,
                    data.posterPath,
                    data.title,
                    data.backdropPath,
                    data.overview,
                    false)

                localDataSource.updateMovieDetail(movie)
            }

        }.asLiveData()
    }

    override fun getTvShowDetail(tvId: Int): LiveData<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, TvShow>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShow> =
                localDataSource.getTvShowDetail(tvId)

            override fun shouldFetch(data: TvShow?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShow>> =
                remoteDataSource.getTvShowDetail(tvId)

            override fun saveCallResult(data: TvShow) {
                val tvShow = TvShow(data.id,
                    data.name,
                    data.posterPath,
                    data.backdropPath,
                    data.overview,
                    false)

                localDataSource.updateTvShowDetail(tvShow)
            }

        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, state) }

    override fun getFavoriteMovies(): LiveData<List<Movie>> =
        localDataSource.getFavoriteMovies()

    override fun getFavoriteTvShows(): LiveData<List<TvShow>> =
        localDataSource.getFavoriteTvShows()

}