package com.example.starwarsdk.interactors.di

import com.example.starwarsdk.interactors.character.CharacterUseCase
import com.example.starwarsdk.interactors.character.CharacterUseCaseImpl
import com.example.starwarsdk.interactors.film.FilmUseCase
import com.example.starwarsdk.interactors.film.FilmUseCaseImpl
import com.example.starwarsdk.interactors.planet.PlanetUseCase
import com.example.starwarsdk.interactors.planet.PlanetUseCaseImpl
import com.example.starwarsdk.network.api.FilmApiService
import com.example.starwarsdk.network.api.PeopleApiService
import com.example.starwarsdk.network.api.PlanetApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Provides
    @Singleton
    fun providePlanetUseCase(apiService: PlanetApiService):
            PlanetUseCase = PlanetUseCaseImpl(apiService = apiService)

    @Provides
    @Singleton
    fun provideFilmUseCase(apiService: FilmApiService):
            FilmUseCase = FilmUseCaseImpl(apiService = apiService)

    @Provides
    @Singleton
    fun provideCharacterUseCase(apiService: PeopleApiService):
            CharacterUseCase = CharacterUseCaseImpl(apiService = apiService)

}