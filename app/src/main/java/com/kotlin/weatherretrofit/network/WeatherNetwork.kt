package com.kotlin.weatherretrofit.network

import com.kotlin.weatherretrofit.network.model.Location
import com.kotlin.weatherretrofit.network.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://www.metaweather.com/api/location/"

interface WeatherNetwork {
    @GET("search?")
    fun getLocation(@Query("query") searchString: String) : Call<List<Location>>

    @GET("{woeid}")
    fun getWeather(@Path("woeid") woeid : Int) : Call<WeatherResponse>

}