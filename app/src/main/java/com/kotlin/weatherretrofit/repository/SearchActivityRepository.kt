package com.kotlin.weatherretrofit.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.kotlin.weatherretrofit.network.BASE_URL
import com.kotlin.weatherretrofit.network.model.Location
import com.kotlin.weatherretrofit.network.WeatherNetwork
import com.kotlin.weatherretrofit.network.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivityRepository (val application: Application){

    val showProgress = MutableLiveData<Boolean>()
    val locationList = MutableLiveData<List<Location>>()

    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun searchLocation(searchString: String) {
        showProgress.value = true
        //Network call
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(WeatherNetwork::class.java)

        service.getLocation(searchString).enqueue(object : Callback<List<Location>> {
            override fun onResponse(call: Call<List<Location>>, response: Response<List<Location>>) {
                Log.d("SearchRepository", "Response: ${Gson().toJson(response.body())}")
                locationList.value = response.body()
                showProgress.value = false
            }

            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, "Error while accessing the API", Toast.LENGTH_SHORT);
            }
        })
    }
}