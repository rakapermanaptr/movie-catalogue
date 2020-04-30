package com.example.moviecatalogue.ui.catalogue.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.utils.*
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]

            viewModel.getMovies().observe(this, Observer { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> showLoading()
                        Status.SUCCESS -> {
                            hideLoading()
                            showMovieList(movies.data)
                        }
                        Status.ERROR -> hideLoading()
                    }
                }
            })
        }

    }

    private fun showMovieList(movies: PagedList<Movie>?) {
        val moviesAdapter = MoviesAdapter()
        moviesAdapter.submitList(movies!!)

        rv_movies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moviesAdapter
        }
    }

    private fun showLoading() {
        progress_bar.show()
    }

    private fun hideLoading() {
        progress_bar.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

}
