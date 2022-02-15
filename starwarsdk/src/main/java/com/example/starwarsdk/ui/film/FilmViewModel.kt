package com.example.starwarsdk.ui.film

import com.example.starwarsdk.datamanager.FilmDataManager
import com.example.starwarsdk.network.response.GetFilmResponse
import com.example.starwarsdk.ui.BaseViewModel
import com.example.starwarsdk.utils.ResultCallback
import com.example.starwarsdk.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(private val filmDataManager: FilmDataManager) :
    BaseViewModel() {
    val filmListObserver = SingleLiveEvent<List<GetFilmResponse>>()
    var showLoading = SingleLiveEvent<Boolean>()

    private val resultCallback: ResultCallback<List<GetFilmResponse>> = object :
        ResultCallback<List<GetFilmResponse>> {
        override fun onShowLoading(isLoading: Boolean) {
            showLoading.postValue(isLoading)
        }

        override fun onResult(result: List<GetFilmResponse>) {
            filmListObserver.postValue(result)
        }
    }

    fun getFilmList() {
        filmDataManager.fetchFilmList(resultCallback)
    }

    override fun onCleared() {
        super.onCleared()
        filmDataManager.closeJob()
    }

}