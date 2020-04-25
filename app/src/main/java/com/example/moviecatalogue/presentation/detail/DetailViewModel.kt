package com.example.moviecatalogue.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.domain.entity.Movie
import com.example.moviecatalogue.domain.entity.TvShow
import com.example.moviecatalogue.utils.DataDummy

class DetailViewModel : ViewModel() {

    private var movieId: Int = 0

    private var tvShowId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): Movie {
        lateinit var movie: Movie
        val movies = DataDummy.generateDummyMovies()
        for (movieEntity in movies) {
            if (movieEntity.id == movieId) {
                movie = movieEntity
            }
        }

        return movie
    }

    fun getTvShow(): TvShow {
        lateinit var tvShow: TvShow
        val tvShows = DataDummy.generateTvShows()
        for (tvShowEntity in tvShows) {
            if (tvShowEntity.id == tvShowId) {
                tvShow = tvShowEntity
            }
        }

        return tvShow
    }

}