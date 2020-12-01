package com.sphinx.movies.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sphinx.movies.data.model.Movie
import com.sphinx.movies.data.service.MovieService
import com.sphinx.movies.data.service.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {
    private val apiService = ServiceBuilder.create(MovieService::class.java)

    val popularMovies = MutableLiveData<ArrayList<Movie>>()
    val topRatedMovies = MutableLiveData<ArrayList<Movie>>()

    fun fetchPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = apiService.fetchPopularMovies()
            if (response.isSuccessful) {
                popularMovies.postValue(response.body()!!.results)
            } else {
                println(response.errorBody())
            }
        } catch (e: Throwable) {
            println(e.message)
        }
    }

    fun fetchTopRatedMovies() = viewModelScope.launch(Dispatchers.IO) {
        val response = apiService.fetchTopRatedMovies()
        try {
            if (response.isSuccessful) {
                topRatedMovies.postValue(response.body()!!.results)
            } else {
                println(response.errorBody())
            }
        } catch (e: Throwable) {
            println(e.message)
        }
    }

}