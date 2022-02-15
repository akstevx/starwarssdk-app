package com.example.starwarsdk.network.api

import com.example.starwarsdk.network.response.GetPlanetResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface PlanetApiService {

    @GET("api/planets/{id}/")
    fun getPlanet(@Path("id") planetID:Int): Deferred<GetPlanetResponse>

}