package com.mobileoak.rickandmorty

interface ResponseHandler {
    fun onDataLoaded(root: Root)
}