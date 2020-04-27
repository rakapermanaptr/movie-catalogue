package com.example.moviecatalogue.presentation.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.Movie
import com.example.moviecatalogue.utils.MOVIE
import com.example.moviecatalogue.utils.NavigationsUtils
import com.example.moviecatalogue.utils.toast
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {

    private val moviesAdapter = MoviesAdapter {movie ->
        context?.toast(movie.title)
        NavigationsUtils.navigateToDetail(requireActivity(), movie.id!!, MOVIE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProviders.of(this)[MoviesViewModel::class.java]
            val movieList = viewModel.getMovies()

            showMovieList(movieList)
        }

    }

    private fun showMovieList(movies: List<Movie>) {
        moviesAdapter.addItems(movies)

        rv_movies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moviesAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

}
