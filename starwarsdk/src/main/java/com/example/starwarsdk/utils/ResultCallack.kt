package com.example.starwarsdk.utils

interface ResultCallback<T> {
    fun onShowLoading( isLoading: Boolean)
    fun onResult( result: T)
}