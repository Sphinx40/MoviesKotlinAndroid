package com.sphinx.movies.services

import com.sphinx.movies.MovieDetails
import com.sphinx.movies.Movies
import retrofit2.Call
import retrofit2.http.*


interface MovieService {
    @GET("movie/popular?")
    fun fetchPopularMovies(
        @Query("page") page: Int = 1
    ): Call<Movies>

    @GET("movie/top_rated?")
    fun fetchTopRatedMovies(
        @Query("page") page: Int = 1
    ): Call<Movies>

    @GET("movie/{movieId}")
    fun fetchMovie(
        @Path("movieId") movieId: Int
    ): Call<MovieDetails>

    @GET("movie/now_playing?")
    fun fetchNowPlayingMovies(
        @Query("page") page: Int = 1
    ): Call<Movies>

    @GET("movie/upcoming?")
    fun fetchUpcomingMovies(
        @Query("page") page: Int = 1
    ): Call<Movies>

    @GET("search/movie?")
    fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): Call<Movies>
}