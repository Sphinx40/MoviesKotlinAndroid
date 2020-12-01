package com.sphinx.movies.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SharedViewModel(application: Application): AndroidViewModel(application) {

    val progressBarVisibility = MutableLiveData<Boolean>(true)

    fun hideProgressBar() {
        progressBarVisibility.value = false
    }
}