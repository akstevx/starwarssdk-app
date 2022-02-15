package com.example.starwarsdk.interactors.planet

import com.example.starwarsdk.exceptions.NetworkException
import com.example.starwarsdk.network.api.PlanetApiService
import com.example.starwarsdk.network.response.GetPlanetResponse
import java.lang.Exception
import javax.inject.Inject

interface PlanetUseCase {
    suspend fun getPlanet(planetID:Int): GetPlanetResponse
}

class PlanetUseCaseImpl @Inject constructor(private val apiService: PlanetApiService): PlanetUseCase {

    override suspend fun getPlanet(planetID: Int): GetPlanetResponse {
        return try { apiService.getPlanet(planetID).await() }
        catch (ex:Exception){ throw NetworkException(ex.toString()) }
    }

}
