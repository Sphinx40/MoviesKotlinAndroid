package com.sphinx.movies.activities

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sphinx.movies.R
import com.sphinx.movies.adapterItems.SearchMovieItem
import com.sphinx.movies.services.ServiceBuilder
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_search_movies.*

class SearchMoviesActivity : AppCompatActivity() {

    private var searchView: SearchView? = null
    private val foundMovies = arrayListOf<SearchMovieItem>()
    private val foundMoviesAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movies)
        setSupportActionBar(search_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
        progressBar2.visibility = View.INVISIBLE

        foundMoviesRecyclerView.layoutManager = LinearLayoutManager(this)
        foundMoviesRecyclerView.adapter = foundMoviesAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        val searchableInfo = searchManager.getSearchableInfo(componentName)
        searchView?.setSearchableInfo(searchableInfo)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                val apiService = ServiceBuilder.create()
                val callSearchMovies = apiService.searchMovies(p0)

                foundMovies.clear()
                foundMoviesAdapter.clear()
//                progressBar2.visibility = View.VISIBLE
//
//                helpers.getMoviesByRow(foundMovies, callSearchMovies){
//                    println(foundMovies.size)
//                    foundMoviesAdapter.addAll(foundMovies)
//                    progressBar2.visibility = View.INVISIBLE
//                }

                println("SSSSSSSS")

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        searchView?.isIconified = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }

}