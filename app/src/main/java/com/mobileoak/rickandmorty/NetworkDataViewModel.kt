package com.mobileoak.rickandmorty

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed class NetworkState {
    object Loading: NetworkState()
    data class DataLoaded(val data: List<Result>): NetworkState()
    data class ErrorState(val error: String = "Error loading data"): NetworkState()
}

class NetworkDataViewModel @ViewModelInject constructor(private val networkManager: NetworkManager) : ViewModel() {
    private val TAG = NetworkDataViewModel::class.simpleName
    private val _state = MutableLiveData<NetworkState>().apply {
        value = NetworkState.Loading
    }

    val state : LiveData<NetworkState>
        get() = _state

    init {
        viewModelScope.launch {
            try {
                val root = networkManager.requestCharacters().results.orEmpty()
                _state.value = NetworkState.DataLoaded(root)
            } catch (exception : Exception) {
                _state.value = NetworkState.ErrorState(exception.message ?: "Network error!")
            }
        }
    }

}