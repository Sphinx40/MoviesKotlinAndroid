package com.sphinx.movies.data.model

data class MovieResponse <T> (
    val page: Int,
    val results: ArrayList<T>
)
