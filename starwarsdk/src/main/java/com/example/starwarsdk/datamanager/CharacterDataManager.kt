package com.example.starwarsdk.datamanager

import com.example.starwarsdk.exceptions.NetworkException
import com.example.starwarsdk.interactors.character.CharacterUseCase
import com.example.starwarsdk.network.response.GetPersonResponse
import com.example.starwarsdk.utils.ResultCallback
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class CharacterDataManager  @Inject constructor(
    private val characterUseCase: CharacterUseCase,
) : CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() =   Dispatchers.Main + job
    private lateinit var characterList: List<GetPersonResponse>
    private var randomList = listOf<Int>()

    fun fetchCharacters(callback: ResultCallback<List<GetPersonResponse>>){
        getRandomCharacterIds()
        callback.onShowLoading(isLoading = true)
        GlobalScope.launch(Dispatchers.IO) {
            try{
                val fetchCharacters = listOf(
                    async { characterUseCase.getPerson(randomList[1]) },
                    async { characterUseCase.getPerson(randomList[2]) },
                    async { characterUseCase.getPerson(randomList[3]) },
                    async { characterUseCase.getPerson(randomList[4]) },
                    async { characterUseCase.getPerson(randomList[5]) }
                )
                characterList = fetchCharacters.awaitAll()
                callback.onShowLoading(isLoading = false)
                callback.onResult(characterList)
            } catch (ex: Exception){
                callback.onShowLoading(isLoading = false)
                throw NetworkException(ex.toString())
            }
        }
    }

    fun closeJob(){
        coroutineContext.cancel()
    }

    private fun getRandomCharacterIds() {
        val s: MutableSet<Int> = mutableSetOf()
        while (s.size <= 5) { s.add((1..10).random()) }
        randomList = s.toList()
    }

}