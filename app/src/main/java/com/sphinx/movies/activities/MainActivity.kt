package com.sphinx.movies.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sphinx.movies.R
import com.sphinx.movies.fragments.FavoriteFragment
import com.sphinx.movies.fragments.HomeFragment
import com.sphinx.movies.fragments.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val favoriteFragment = FavoriteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Initialize home fragment and bottom navigation listener
        setCurrentFragment(homeFragment)
        main_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> setCurrentFragment(homeFragment)

                R.id.nav_search -> setCurrentFragment(searchFragment)

                R.id.nav_favoriteMovies -> setCurrentFragment(favoriteFragment)
            }

            true
        }
    }


    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_frame,fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }
}