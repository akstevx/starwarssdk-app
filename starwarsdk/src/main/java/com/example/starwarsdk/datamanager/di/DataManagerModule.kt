package com.example.starwarsdk.datamanager.di

import com.example.starwarsdk.datamanager.CharacterDataManager
import com.example.starwarsdk.datamanager.FilmDataManager
import com.example.starwarsdk.datamanager.PlanetDataManager
import com.example.starwarsdk.interactors.character.CharacterUseCase
import com.example.starwarsdk.interactors.film.FilmUseCase
import com.example.starwarsdk.interactors.planet.PlanetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataManagerModule {

    @Provides
    @Singleton
    fun providePlanetDataManager(planetUseCase: PlanetUseCase): PlanetDataManager =
        PlanetDataManager(planetUseCase)

    @Provides
    @Singleton
    fun provideFilmDataManager(filmUseCase: FilmUseCase): FilmDataManager =
        FilmDataManager(filmUseCase)

    @Provides
    @Singleton
    fun provideCharacterDataManager(characterUseCase: CharacterUseCase): CharacterDataManager =
        CharacterDataManager(characterUseCase)
}