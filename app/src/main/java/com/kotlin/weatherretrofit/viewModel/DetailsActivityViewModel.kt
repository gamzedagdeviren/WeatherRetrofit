package com.kotlin.weatherretrofit.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kotlin.weatherretrofit.network.model.ConsolidatedWeather
import com.kotlin.weatherretrofit.network.model.WeatherResponse
import com.kotlin.weatherretrofit.repository.DetailsActivityRepository

class DetailsActivityViewModel(application: Application) : AndroidViewModel(application) {
    val repository = DetailsActivityRepository(application)
    val showProgress : LiveData<Boolean>
    val response : LiveData<WeatherResponse>

    init {
        showProgress = repository.showProgress
        response = repository.response
    }

    fun getWeather(woeId: Int) {
        repository.getWeather(woeId)
    }
}