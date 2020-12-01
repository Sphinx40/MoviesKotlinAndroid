package com.sphinx.movies.data.service

import com.sphinx.movies.data.model.MovieResponse
import com.sphinx.movies.data.model.Movie
import retrofit2.Response
import retrofit2.http.*

interface MovieService {
    @GET("movie/popular?")
    suspend fun fetchPopularMovies(
        @Query("page") page: Int = 1
    ): Response<MovieResponse<Movie>>

    @GET("movie/top_rated?")
    suspend fun fetchTopRatedMovies(
        @Query("page") page: Int = 1
    ): Response<MovieResponse<Movie>>

//    @GET("movie/{movieId}")
//    fun fetchMovie(
//        @Path("movieId") movieId: Int
//    ): Call<MovieDetails>
//
//    @GET("movie/now_playing?")
//    fun fetchNowPlayingMovies(
//        @Query("page") page: Int = 1
//    ): Call<Movies>
//
//    @GET("movie/upcoming?")
//    fun fetchUpcomingMovies(
//        @Query("page") page: Int = 1
//    ): Call<Movies>
//
//    @GET("search/movie?")
//    fun searchMovies(
//        @Query("query") query: String,
//        @Query("page") page: Int = 1
//    ): Call<Movies>
}