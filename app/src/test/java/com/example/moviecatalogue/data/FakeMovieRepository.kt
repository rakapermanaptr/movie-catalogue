package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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
    private val appExecutors: AppExecutors
) :
    IMovieRepository {

    override fun getMovies(): LiveData<Resource<PagedList<Movie>>> {
        return object : NetworkBoundResource<PagedList<Movie>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<Movie>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> {
                return remoteDataSource.getAllMovies()
            }

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<Movie>()
                for (response in data) {
                    val movie = Movie(
                        response.id,
                        response.posterPath,
                        response.title,
                        response.backdropPath,
                        response.overview
                    )

                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }


    override fun getTvShows(): LiveData<Resource<PagedList<TvShow>>> {
        return object : NetworkBoundResource<PagedList<TvShow>, List<TvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShow>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(10)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShow>?): Boolean {
                return data == null || data.isEmpty()

            }

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> {
                return remoteDataSource.getAllTvShows()
            }

            override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShow>()
                for (response in data) {
                    val tvShow = TvShow(
                        response.id,
                        response.name,
                        response.posterPath,
                        response.backdropPath,
                        response.overview
                    )

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
                val movie = Movie(
                    data.id,
                    data.posterPath,
                    data.title,
                    data.backdropPath,
                    data.overview,
                    false
                )

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
                val tvShow = TvShow(
                    data.id,
                    data.name,
                    data.posterPath,
                    data.backdropPath,
                    data.overview,
                    false
                )

                localDataSource.updateTvShowDetail(tvShow)
            }

        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, state) }

    override fun getFavoriteMovies(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShow>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

}