package com.example.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.ViewPagerAdapter
import com.example.moviecatalogue.ui.favorite.movies.FavMoviesFragment
import com.example.moviecatalogue.ui.favorite.tvshows.FavTvShowsFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupView()

    }

    private fun setupView() {
        val favoriteSectionsAdapter = ViewPagerAdapter(childFragmentManager)
        favoriteSectionsAdapter.populateFragment(FavMoviesFragment(), "MOVIES")
        favoriteSectionsAdapter.populateFragment(FavTvShowsFragment(), "TV SHOWS")

        view_pager.adapter = favoriteSectionsAdapter
        tabs.setupWithViewPager(view_pager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

}
