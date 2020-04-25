package com.example.moviecatalogue.presentation.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.moviecatalogue.R

/**
 * A simple [Fragment] subclass.
 */
class TvShowsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_shows, container, false)
    }

}
