package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.utils.*
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    private var menu: Menu? = null

    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Initialize id and type from MoviesFragment and TvShowsFragment
        val id = intent.getIntExtra(KEY_ID, 0)
        type = intent.getStringExtra(KEY_TYPE)!!

        // Initialize viewModel factory and viewModel
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        // Condition type.
        when (type) {
            MOVIE -> {
                viewModel.setSelectedMovie(id)
                viewModel.movie.observe(this, Observer { movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> showLoading()
                            Status.SUCCESS -> {
                                hideLoading()
                                showMovieDetail(movie.data)
                            }
                            Status.ERROR -> hideLoading()
                        }
                    }
                })
            }
            TV_SHOW -> {
                viewModel.setSelectedTvShow(id)
                viewModel.tvShow.observe(this, Observer { tvShow ->
                    if (tvShow != null) {
                        when (tvShow.status) {
                            Status.LOADING -> showLoading()
                            Status.SUCCESS -> {
                                hideLoading()
                                showTvShowDetail(tvShow.data)
                            }
                            Status.ERROR -> hideLoading()
                        }
                    }
                })
            }
        }

    }

    private fun showMovieDetail(movie: Movie?) {
        Glide.with(this)
            .load(BASE_IMAGE_URL + movie?.backdropPath)
            .into(img_backdrop)

        tv_title.text = movie?.title
        tv_overview.text = movie?.overview
    }

    private fun showTvShowDetail(tvShow: TvShow?) {
        Glide.with(this)
            .load(BASE_IMAGE_URL + tvShow?.backdropPath)
            .into(img_backdrop)

        tv_title.text = tvShow?.name
        tv_overview.text = tvShow?.overview
    }

    private fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        when (type) {
            MOVIE -> {
                viewModel.movie.observe(this, Observer { movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> hideLoading()
                            Status.SUCCESS -> if (movie.data != null) {
                                hideLoading()
                                val state = movie.data.isFavorite
                                setFavoriteState(state)
                            }
                            Status.ERROR -> {
                                hideLoading()
                                this.toast("Terjadi kesalahan")
                            }
                        }
                    }
                })
            }
            TV_SHOW -> {
                viewModel.tvShow.observe(this, Observer { tvShow ->
                    if (tvShow != null) {
                        when (tvShow.status) {
                            Status.LOADING -> hideLoading()
                            Status.SUCCESS -> if (tvShow.data != null) {
                                hideLoading()
                                val state = tvShow.data.isFavorite
                                setFavoriteState(state)
                            }
                            Status.ERROR -> {
                                hideLoading()
                                this.toast("Terjadi kesalahan")
                            }
                        }
                    }
                })
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            when (type) {
                MOVIE -> viewModel.setFavoriteMovie()
                TV_SHOW -> viewModel.setFavoriteTvShow()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorited_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        }
    }
}
