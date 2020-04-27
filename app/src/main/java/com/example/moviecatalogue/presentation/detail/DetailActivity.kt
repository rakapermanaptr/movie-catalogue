package com.example.moviecatalogue.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.utils.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Initialize id and type from MoviesFragment and TvShowsFragment
        val id = intent.getIntExtra(KEY_ID, 0)
        val type = intent.getStringExtra(KEY_TYPE)

        // Initialize viewModel
        val viewModel = ViewModelProviders.of(this)[DetailViewModel::class.java]

        // Condition type.
        when (type) {
            MOVIE -> {
                viewModel.setSelectedMovie(id)

                showMovieDetail(viewModel.getMovie())
            }
            TV_SHOW -> {
                viewModel.setSelectedTvShow(id)

                showTvShowDetail(viewModel.getTvShow())
            }
        }

    }

    private fun showMovieDetail(movie: Movie) {
        Glide.with(this)
            .load(BASE_IMAGE_URL + movie.backdropPath)
            .into(img_backdrop)

        tv_title.text = movie.title
        tv_overview.text = movie.overview
    }

    private fun showTvShowDetail(tvShow: TvShow) {
        Glide.with(this)
            .load(BASE_IMAGE_URL + tvShow.backdropPath)
            .into(img_backdrop)

        tv_title.text = tvShow.name
        tv_overview.text = tvShow.overview
    }
}
