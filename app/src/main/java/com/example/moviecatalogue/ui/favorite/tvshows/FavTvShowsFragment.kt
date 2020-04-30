package com.example.moviecatalogue.ui.favorite.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.TvShow
import com.example.moviecatalogue.utils.hide
import com.example.moviecatalogue.utils.show
import com.example.moviecatalogue.utils.toast
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_fav_tv_shows.*

/**
 * A simple [Fragment] subclass.
 */
class FavTvShowsFragment : Fragment() {

    private lateinit var viewModel: FavTvShowsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProviders.of(this, factory)[FavTvShowsViewModel::class.java]

        observeViewModel()

    }

    private fun observeViewModel() {
        showLoading()
        viewModel.getFavoriteTvShows().observe(this, Observer { tvShows ->
            if (tvShows != null) {
                hideLoading()
                showFavTvShowsList(tvShows)
            }
        })
    }

    private fun showFavTvShowsList(tvShows: PagedList<TvShow>?) {
        val favTvShowsAdapter = FavTvShowsAdapter()
        favTvShowsAdapter.submitList(tvShows!!)

        rv_tvShows.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favTvShowsAdapter
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
        return inflater.inflate(R.layout.fragment_fav_tv_shows, container, false)
    }

}
