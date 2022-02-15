package com.example.starwarsdk.interactors.character

import com.example.starwarsdk.exceptions.NetworkException
import com.example.starwarsdk.network.api.FilmApiService
import com.example.starwarsdk.network.api.PeopleApiService
import com.example.starwarsdk.network.response.GetFilmResponse
import com.example.starwarsdk.network.response.GetPersonResponse
import retrofit2.await
import java.lang.Exception
import javax.inject.Inject

interface CharacterUseCase {
    suspend fun getPerson(personId:Int): GetPersonResponse
}

class CharacterUseCaseImpl @Inject constructor(private val apiService: PeopleApiService): CharacterUseCase {
    override suspend fun getPerson(personId:Int): GetPersonResponse {
        return try { apiService.getPerson(personId).await() }
        catch (ex: Exception){ throw NetworkException(ex.toString()) }
    }

}
