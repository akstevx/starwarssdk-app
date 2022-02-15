package com.example.starwarsdk.ui.planet

import com.example.starwarsdk.datamanager.PlanetDataManager
import com.example.starwarsdk.network.response.GetPlanetResponse
import com.example.starwarsdk.ui.BaseViewModel
import com.example.starwarsdk.utils.ResultCallback
import com.example.starwarsdk.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(private val planetDataManager: PlanetDataManager) :
    BaseViewModel() {
    val planetsObserver = SingleLiveEvent<List<GetPlanetResponse>>()
    var showLoading = SingleLiveEvent<Boolean>()

    private val resultCallback: ResultCallback<List<GetPlanetResponse>> = object :
        ResultCallback<List<GetPlanetResponse>> {
        override fun onShowLoading(isLoading: Boolean) {
            showLoading.postValue(isLoading)
        }

        override fun onResult(result: List<GetPlanetResponse>) {
            planetsObserver.postValue(result)
        }
    }

    fun getPlanets() {
        planetDataManager.fetchPlanets(resultCallback)
    }

    override fun onCleared() {
        super.onCleared()
        planetDataManager.closeJob()
    }

}