package com.kotlin.weatherretrofit.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kotlin.weatherretrofit.network.model.Location
import com.kotlin.weatherretrofit.repository.SearchActivityRepository

class SearchActivityViewModel (application: Application) : AndroidViewModel(application) {

    val repository = SearchActivityRepository(application)
    val showProgress : LiveData<Boolean>
    val locationList : LiveData<List<Location>>

    init {
        this.showProgress = repository.showProgress
        this.locationList = repository.locationList
    }

    fun changeState() {
        repository.changeState()
    }

    fun searchLocation(searchString: String) {
        repository.searchLocation(searchString)
    }
}