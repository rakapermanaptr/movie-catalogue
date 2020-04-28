package com.example.moviecatalogue.ui.favorite.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.utils.toast
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_fav_movies.*

/**
 * A simple [Fragment] subclass.
 */
class FavMoviesFragment : Fragment() {

    private val favMoviesAdapter = FavMoviesAdapter {favMovie ->
        context?.toast(favMovie.title)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        val viewModel = ViewModelProviders.of(this, factory)[FavMoviesViewModel::class.java]

        showLoading()
        viewModel.getFavoriteMovies().observe(this, Observer { favMovies ->
            if (favMovies != null) {
                hideLoading()
                showMovieList(favMovies)
            }
        })
    }

    private fun showMovieList(movies: List<Movie>?) {
        favMoviesAdapter.addItems(movies!!)

        rv_movies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favMoviesAdapter
        }
    }

    private fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movies, container, false)
    }

}
