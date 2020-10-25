package com.sphinx.movies.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sphinx.movies.*

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        // Get clicked movie
        val intent = intent
        val movieId: Int = intent.getIntExtra("movieId", 1)

    }
}