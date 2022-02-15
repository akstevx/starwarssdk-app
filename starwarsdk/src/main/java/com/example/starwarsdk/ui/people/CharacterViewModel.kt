package com.example.starwarsdk.ui.people

import com.example.starwarsdk.datamanager.CharacterDataManager
import com.example.starwarsdk.network.response.GetPersonResponse
import com.example.starwarsdk.ui.BaseViewModel
import com.example.starwarsdk.utils.ResultCallback
import com.example.starwarsdk.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterDataManager: CharacterDataManager) :
    BaseViewModel() {
    val characterListObserver = SingleLiveEvent<List<GetPersonResponse>>()
    var showLoading = SingleLiveEvent<Boolean>()

    private val resultCallback: ResultCallback<List<GetPersonResponse>> = object :
        ResultCallback<List<GetPersonResponse>> {
        override fun onShowLoading(isLoading: Boolean) {
            showLoading.postValue(isLoading)
        }

        override fun onResult(result: List<GetPersonResponse>) {
            characterListObserver.postValue(result)
        }
    }

    fun getCharacters() {
        characterDataManager.fetchCharacters(resultCallback)
    }

    override fun onCleared() {
        super.onCleared()
        characterDataManager.closeJob()
    }

}