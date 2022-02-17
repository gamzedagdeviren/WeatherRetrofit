package com.kotlin.weatherretrofit.view

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlin.weatherretrofit.R
import com.kotlin.weatherretrofit.adapter.LocationAdapter
import com.kotlin.weatherretrofit.adapter.WeatherAdapter
import com.kotlin.weatherretrofit.viewModel.DetailsActivityViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_search.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsActivityViewModel
    private lateinit var adapter : WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        viewModel = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)

        if(intent.hasExtra("name")) {
            txt_location.text = intent.getStringExtra("name")
        }

        if(intent.hasExtra("Location")) {
            //  do network call
            val location = intent.getIntExtra("Location", 0)
            if(location > 0)
                viewModel.getWeather(location)

        }

        viewModel.showProgress.observe(this, Observer {
            if (it)
                details_progress.visibility = VISIBLE
            else
                details_progress.visibility = GONE
        })

        viewModel.response.observe(this, Observer {
            if (it != null) {
                adapter.setWeatherList(it.consolidated_weather)
            }
        })
        adapter = WeatherAdapter(this)
        recycler_weathers.adapter = adapter
    }
}