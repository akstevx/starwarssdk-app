package com.example.starwarsdk.interactors.film

import com.example.starwarsdk.exceptions.NetworkException
import com.example.starwarsdk.network.api.FilmApiService
import com.example.starwarsdk.network.response.GetFilmResponse
import java.lang.Exception
import javax.inject.Inject

interface FilmUseCase {
    suspend fun getFilm(filmId:Int): GetFilmResponse
}

class FilmUseCaseImpl @Inject constructor(private val apiService: FilmApiService): FilmUseCase {

    override suspend fun getFilm(filmId:Int): GetFilmResponse {
        return try { apiService.getFilm(filmId).await() }
        catch (ex: Exception){ throw NetworkException(ex.toString()) }
    }
}
