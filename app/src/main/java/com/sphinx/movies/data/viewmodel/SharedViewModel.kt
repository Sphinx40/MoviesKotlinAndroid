package com.sphinx.movies.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sphinx.movies.data.model.Movie

class SharedViewModel(application: Application): AndroidViewModel(application) {

    val emptyData = MutableLiveData<Boolean>(true)
    val dataReceived = MutableLiveData<Boolean>(false)

    fun checkData(data: ArrayList<Movie>) {
        emptyData.value = data.isEmpty()
        dataReceived.value = true
    }
}