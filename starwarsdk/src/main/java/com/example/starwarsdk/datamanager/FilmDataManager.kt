package com.example.starwarsdk.datamanager

import com.example.starwarsdk.exceptions.NetworkException
import com.example.starwarsdk.interactors.film.FilmUseCase
import com.example.starwarsdk.network.response.GetFilmResponse
import com.example.starwarsdk.utils.ResultCallback
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FilmDataManager  @Inject constructor(
    private val filmUseCase: FilmUseCase,
) : CoroutineScope  {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() =   Dispatchers.Main + job
    private var randomList = listOf<Int>()
    private lateinit var filmList: List<GetFilmResponse>

    fun fetchFilmList(callback: ResultCallback<List<GetFilmResponse>>){
        getRandomFilmIds()
        callback.onShowLoading(isLoading = true)
        GlobalScope.launch(Dispatchers.IO) {
            try{
                val fetchMovies = listOf(
                    // fetch planets at the same time
                    async { filmUseCase.getFilm(randomList[1]) },
                    async { filmUseCase.getFilm(randomList[2]) },
                    async { filmUseCase.getFilm(randomList[3]) },
                    async { filmUseCase.getFilm(randomList[4]) },
                    async { filmUseCase.getFilm(randomList[5]) },
                )
                filmList = fetchMovies.awaitAll()
                callback.onShowLoading(isLoading = false)
                callback.onResult(filmList)
            } catch (ex: Exception){
                callback.onShowLoading(isLoading = false)
                throw NetworkException(ex.toString())
            }
        }
    }

    fun closeJob(){
        coroutineContext.cancel()
    }

    private fun getRandomFilmIds() {
        val s: MutableSet<Int> = mutableSetOf()
        while (s.size <= 5) { s.add((1..6).random()) }
        randomList = s.toList()
    }

}