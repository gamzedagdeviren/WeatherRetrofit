package com.kotlin.weatherretrofit.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.weatherretrofit.R
import com.kotlin.weatherretrofit.network.model.Location
import com.kotlin.weatherretrofit.view.DetailsActivity
import kotlinx.android.synthetic.main.rv_location_child.view.*

class LocationAdapter (private val context: Context) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    private var list: List<Location> = ArrayList()

    fun setLocationList (list: List<Location>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.rv_location_child,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("adapter name:", "${list[position].title}")
        holder.name.text = list[position].title
        holder.latLng.text = list[position].latt_long
        holder.rootView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("Location", list[position].woeid)
            intent.putExtra("name", list[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size


    class ViewHolder (v: View) : RecyclerView.ViewHolder(v) {
        val name = v.txt_location_name!!
        val latLng = v.txt_lat_lng!!
        val rootView = v.child_root!!
    }

}