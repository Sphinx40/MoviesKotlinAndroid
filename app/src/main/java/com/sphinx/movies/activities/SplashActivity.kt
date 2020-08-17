package com.sphinx.movies.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sphinx.movies.utils.Globals
import com.sphinx.movies.R
import com.sphinx.movies.services.ServiceBuilder
import com.sphinx.movies.utils.helpers
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        logotype.alpha = 0f
        logotype.animate().setDuration(1500).alpha(1f)

        val apiService = ServiceBuilder.create()
        val callPopularMovies = apiService.fetchPopularMovies()
        val callTopRatedMovies = apiService.fetchTopRatedMovies()
        val callNowPlayingMovies = apiService.fetchNowPlayingMovies()
        val callUpcomingMovies = apiService.fetchUpcomingMovies()

        val popularMovies = Globals.popularMovies
        val topRatedMovies = Globals.topRatedMovies
        val nowPlayingMovies = Globals.nowPlayingMovies
        val upcomingMovies = Globals.upcomingMovies

        helpers.getMoviesBySmallCard(popularMovies, callPopularMovies)
        helpers.getMoviesBySmallCard(topRatedMovies, callTopRatedMovies)
        helpers.getMoviesByRow(nowPlayingMovies, callNowPlayingMovies)
        helpers.getMoviesByRow(upcomingMovies, callUpcomingMovies){
            goToMainActivity()
        }
    }

    private fun goToMainActivity () {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}