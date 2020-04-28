package com.example.moviecatalogue.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.catalogue.CatalogueFragment
import com.example.moviecatalogue.ui.favorite.FavoriteFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_catalogue -> {
                    fragment = CatalogueFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_favorite -> {
                    fragment = FavoriteFragment()
                    loadFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // remove shadow on toolbar
        supportActionBar?.elevation = 0f

        if (savedInstanceState == null) {
            loadFragment(CatalogueFragment())
        }

        bttm_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}
