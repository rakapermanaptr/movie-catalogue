package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.vo.Resource

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var movieId = MutableLiveData<Int>()

    private var tvShowId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    var movie: LiveData<Resource<Movie>> = Transformations.switchMap(movieId) { mMovieId ->
        movieRepository.getMovieDetail(mMovieId)
    }

    var tvShow: LiveData<Resource<TvShow>> = Transformations.switchMap(tvShowId) { mTvShowId ->
        movieRepository.getTvShowDetail(mTvShowId)
    }

    fun setFavoriteMovie() {
        val movieResource = movie.value
        if (movieResource != null) {
            val movie = movieResource.data

            if (movie != null) {
                val movieEntity = movie
                val newState = !movieEntity.isFavorite
                movieRepository.setFavoriteMovie(movieEntity, newState)
            }
        }
    }

    fun setFavoriteTvShow() {
        val tvShowResource = tvShow.value
        if (tvShowResource != null) {
            val tvShow = tvShowResource.data

            if (tvShow != null) {
                val tvShowEntitiy = tvShow
                val newState = !tvShowEntitiy.isFavorite
                movieRepository.setFavoriteTvShow(tvShowEntitiy, newState)
            }
        }
    }


}