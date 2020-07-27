package com.sphinx.movies.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sphinx.movies.*
import com.sphinx.movies.services.ServiceBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        hideElements()

        // Get clicked movie
        val intent = intent
        val movieId: Int = intent.getIntExtra("movieId", 1)

        val apiService = ServiceBuilder.create()
        val callFetchMovieById = apiService.fetchMovie(movieId)
        val imageUrl = Config.IMAGE_URL
        val imageSizeBig = Config.IMAGE_SIZE_BIG

        callFetchMovieById.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                if (response.code() == 200) {
                    var genresText = ""
                    val res = response.body()!!
                    println(res)
                    Picasso
                        .get()
                        .load(imageUrl + imageSizeBig + res.backdrop_path)
                        .into(movieDetailsImage)
                    movieDetailsTitle.text = res.title
                    progressBar.visibility = View.INVISIBLE
                    overview.text = "${res.overview}"
                    originalLanguage.text = "Язык: ${res.original_language}"
                    release_date.text = "Дата выхода: ${res.release_date}"
                    for (genre in res.genres) {
                        genresText += genre.name+", "
                    }
                    genres.text = "Жанры: $genresText"
                    budget.text = "Бюджет: ${res.budget}"
                    showElements()
                }
            }
            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun showElements() {
        val visible = View.VISIBLE
        movieDetailsImage.visibility = visible
        movieDetailsTitle.visibility = visible
        arrow_back.visibility = visible
        favoriteMovie.visibility = visible
        overview.visibility = visible
        originalLanguage.visibility = visible
        release_date.visibility = visible
        genres.visibility = visible
        budget.visibility = visible
    }

    private fun hideElements() {
        val invisible = View.INVISIBLE
        movieDetailsImage.visibility = invisible
        movieDetailsTitle.visibility = invisible
        arrow_back.visibility = invisible
        favoriteMovie.visibility = invisible
        overview.visibility = invisible
        originalLanguage.visibility = invisible
        release_date.visibility = invisible
        genres.visibility = invisible
        budget.visibility = invisible
    }

    fun goBack(view: View) {
        onBackPressed()
    }
}