package com.mobileoak.rickandmorty

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class NetworkManager(private val context: Context, private val responseHandler: ResponseHandler) {
    private final val TAG = NetworkManager::class.simpleName

    fun requestCharacters() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = "http://rickandmortyapi.com/api/character/"

        // Request a string response from the provided URL.

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val root = Gson().fromJson(response, Root::class.java)
                responseHandler.onDataLoaded(root)
            },
            Response.ErrorListener {
                Log.e(TAG, "That didn't work:" + it.networkResponse)
            }
        )

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }

}