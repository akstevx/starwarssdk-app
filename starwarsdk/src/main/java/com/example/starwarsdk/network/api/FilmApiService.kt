package com.example.starwarsdk.network.api

import com.example.starwarsdk.network.response.GetFilmResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface FilmApiService {
    @GET("api/films/{id}/")
    fun getFilm(@Path("id") filmID:Int): Deferred<GetFilmResponse>
}