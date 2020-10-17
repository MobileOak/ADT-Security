package com.mobileoak.rickandmorty

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: CharacterAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var loadingTextView: TextView
    private lateinit var errorTextView: TextView


    private val viewModel: NetworkDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewManager = LinearLayoutManager(this)

        viewAdapter = CharacterAdapter(emptyList(), this)

        loadingTextView = findViewById(R.id.loading_text)
        errorTextView = findViewById(R.id.error_text)
        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        viewModel.state.observe(this, Observer {
            when (it) {
                NetworkState.Loading -> onLoading()
                is NetworkState.DataLoaded -> onDataLoaded(it.data)
                is NetworkState.ErrorState -> onError(it.error)
            }
        })

    }

    private fun onLoading() {
        loadingTextView.visibility = View.VISIBLE
        errorTextView.visibility = View.GONE
        recyclerView.visibility = View.GONE
    }

    private fun onError(error: String) {
        errorTextView.visibility = View.VISIBLE
        errorTextView.text = error
        recyclerView.visibility = View.GONE
        loadingTextView.visibility = View.GONE
    }

    private fun onDataLoaded(characterList: List<Result>) {
        recyclerView.visibility = View.VISIBLE
        loadingTextView.visibility = View.GONE
        errorTextView.visibility = View.GONE

        viewAdapter.updateData(characterList)
        viewAdapter.notifyDataSetChanged()
    }
}