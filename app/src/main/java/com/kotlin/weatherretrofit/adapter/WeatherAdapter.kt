package com.kotlin.weatherretrofit.adapter

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.weatherretrofit.R
import com.kotlin.weatherretrofit.network.model.ConsolidatedWeather
import kotlinx.android.synthetic.main.rv_weather_list.view.*

class WeatherAdapter (private val context: Context) :
        RecyclerView.Adapter<WeatherAdapter.ViewHolder2>() {
    private var list: List<ConsolidatedWeather> = ArrayList()

    fun setWeatherList (list: List<ConsolidatedWeather>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        return ViewHolder2(
                LayoutInflater.from(context).inflate(
                        R.layout.rv_weather_list,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        Log.d("adapter date:", "${list[position].applicable_date}")
        holder.date.text = list[position].applicable_date
        holder.status.text = list[position].weather_state_name
        holder.degree.text = list[position].the_temp.toString()
        holder.minDegree.text = list[position].min_temp.toString()
        holder.maxDegree.text = list[position].max_temp.toString()
        holder.date.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)
    }

    override fun getItemCount() = list.size

    class ViewHolder2 (v: View) : RecyclerView.ViewHolder(v) {
        val date = v.txt_date!!
        val status = v.txt_status!!
        val degree = v.txt_temp!!
        val minDegree = v.txt_min_degree!!
        val maxDegree = v.txt_max_degree!!
    }
}