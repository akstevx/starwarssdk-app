package com.example.starwarsdk.datamanager

import com.example.starwarsdk.exceptions.NetworkException
import com.example.starwarsdk.interactors.planet.PlanetUseCase
import com.example.starwarsdk.network.response.GetPlanetResponse
import com.example.starwarsdk.utils.ResultCallback
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PlanetDataManager  @Inject constructor(
    private val planetUseCase: PlanetUseCase,
) : CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext get() =  Dispatchers.Main + job
    private lateinit var planets: List<GetPlanetResponse>
    private var randomList = listOf<Int>()

    fun fetchPlanets(callback: ResultCallback<List<GetPlanetResponse>>){
        getRandomIds()
        callback.onShowLoading(isLoading = true)
        GlobalScope.launch(Dispatchers.IO) {
            try{
                val fetchPlanets = listOf(
                    // fetch planets at the same time
                    async { planetUseCase.getPlanet(randomList[1]) },
                    async { planetUseCase.getPlanet(randomList[2]) },
                    async { planetUseCase.getPlanet(randomList[3]) },
                    async { planetUseCase.getPlanet(randomList[4]) },
                    async { planetUseCase.getPlanet(randomList[5]) }
                )
                planets = fetchPlanets.awaitAll()
                callback.onShowLoading(isLoading = false)
                callback.onResult(planets)
            } catch (ex:Exception){
                callback.onShowLoading(isLoading = false)
                throw NetworkException(ex.toString())
            }
        }
    }

    fun closeJob(){
        coroutineContext.cancel()
    }

    private fun getRandomIds() {
        val s: MutableSet<Int> = mutableSetOf()
        while (s.size <= 5) { s.add((1..10).random()) }
        randomList = s.toList()
    }

}