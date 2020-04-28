package com.example.moviecatalogue.ui.catalogue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.ViewPagerAdapter
import com.example.moviecatalogue.ui.catalogue.movies.MoviesFragment
import com.example.moviecatalogue.ui.catalogue.tvshows.TvShowsFragment
import kotlinx.android.synthetic.main.fragment_catalogue.*

/**
 * A simple [Fragment] subclass.
 */
class CatalogueFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupView()

    }

    private fun setupView() {
        val catalogueSectionsAdapter = ViewPagerAdapter(childFragmentManager)
        catalogueSectionsAdapter.populateFragment(MoviesFragment(), "MOVIES")
        catalogueSectionsAdapter.populateFragment(TvShowsFragment(), "TV SHOWS")

        view_pager.adapter = catalogueSectionsAdapter
        tabs.setupWithViewPager(view_pager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalogue, container, false)
    }

}
