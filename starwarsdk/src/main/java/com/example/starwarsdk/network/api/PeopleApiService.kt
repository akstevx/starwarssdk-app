package com.example.starwarsdk.network.api

import com.example.starwarsdk.network.response.GetPersonResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface PeopleApiService {
    @GET("api/people/{id}/")
    fun getPerson(@Path("id") personID:Int): Deferred<GetPersonResponse>
}