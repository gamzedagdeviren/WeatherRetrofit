package com.kotlin.weatherretrofit.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlin.weatherretrofit.R
import com.kotlin.weatherretrofit.adapter.LocationAdapter
import com.kotlin.weatherretrofit.viewModel.SearchActivityViewModel
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {
    private lateinit var viewModel : SearchActivityViewModel
    private lateinit var adapter : LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        viewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)


        viewModel.showProgress.observe(this, Observer {
            if (it)
                search_progress.visibility = VISIBLE
            else
                search_progress.visibility = GONE
        })

        viewModel.locationList.observe(this, Observer {
            adapter.setLocationList(it)
        })

        adapter = LocationAdapter(this)
        rv_search.adapter = adapter

        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || actionId == KeyEvent.ACTION_DOWN
                    && actionId == KeyEvent.KEYCODE_ENTER) {
                viewModel.searchLocation(et_search.text.toString())
            }
            true
        }
    }
}