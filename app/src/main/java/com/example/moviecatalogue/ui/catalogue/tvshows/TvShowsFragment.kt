package com.example.moviecatalogue.ui.catalogue.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.utils.NavigationsUtils
import com.example.moviecatalogue.utils.TV_SHOW
import com.example.moviecatalogue.utils.toast
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.fragment_tv_shows.*

/**
 * A simple [Fragment] subclass.
 */
class TvShowsFragment : Fragment() {

    private val tvShowsAdapter = TvShowsAdapter { tvShow ->
        context?.toast(tvShow.name)
        NavigationsUtils.navigateToDetail(requireActivity(), tvShow.id!!, TV_SHOW)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            viewModel.getTvShows().observe(this, Observer { tvShows ->
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> showLoading()
                        Status.SUCCESS -> {
                            hideLoading()
                            showTvShows(tvShows.data)
                        }
                        Status.ERROR -> hideLoading()
                    }
                }
            })
        }
    }

    private fun showTvShows(tvShows: List<TvShow>?) {
        tvShowsAdapter.addItems(tvShows!!)

        rv_tvShows.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tvShowsAdapter
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
        return inflater.inflate(R.layout.fragment_tv_shows, container, false)
    }

}
