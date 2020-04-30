package com.example.moviecatalogue.ui.favorite.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.utils.hide
import com.example.moviecatalogue.utils.show
import com.example.moviecatalogue.utils.toast
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_fav_movies.*

/**
 * A simple [Fragment] subclass.
 */
class FavMoviesFragment : Fragment() {

    private lateinit var viewModel: FavMoviesViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProviders.of(this, factory)[FavMoviesViewModel::class.java]

        observeViewModel()

    }

    private fun observeViewModel() {
        showLoading()
        viewModel.getFavoriteMovies().observe(this, Observer { favMovies ->
            if (favMovies != null) {
                hideLoading()
                showMovieList(favMovies)
            }
        })
    }

    private fun showMovieList(movies: PagedList<Movie>?) {
        val favMoviesAdapter = FavMoviesAdapter()
        favMoviesAdapter.submitList(movies!!)

        rv_movies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favMoviesAdapter
        }
    }

    private fun showLoading() {
        progress_bar.show()
    }

    private fun hideLoading() {
        progress_bar.hide()
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movies, container, false)
    }

}
