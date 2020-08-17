package com.sphinx.movies.utils

import com.sphinx.movies.adapterItems.HomeMovieItem
import com.sphinx.movies.adapterItems.SearchMovieItem

class Globals {
    companion object {
        val popularMovies = arrayListOf<HomeMovieItem>()
        val topRatedMovies = arrayListOf<HomeMovieItem>()
        val nowPlayingMovies = arrayListOf<SearchMovieItem>()
        val upcomingMovies = arrayListOf<SearchMovieItem>()
    }
}