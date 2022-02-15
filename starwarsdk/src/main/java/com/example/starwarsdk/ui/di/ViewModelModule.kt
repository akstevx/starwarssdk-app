package com.example.starwarsdk.ui.di

import com.example.starwarsdk.datamanager.CharacterDataManager
import com.example.starwarsdk.datamanager.FilmDataManager
import com.example.starwarsdk.datamanager.PlanetDataManager
import com.example.starwarsdk.ui.film.FilmViewModel
import com.example.starwarsdk.ui.people.CharacterViewModel
import com.example.starwarsdk.ui.planet.PlanetViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    @Singleton
    fun providePlanetViewModel (planetDataManager: PlanetDataManager): PlanetViewModel =
        PlanetViewModel(planetDataManager)

    @Provides
    @Singleton
    fun provideCharacterViewModel (characterDataManager: CharacterDataManager): CharacterViewModel =
        CharacterViewModel(characterDataManager)

    @Provides
    @Singleton
    fun provideFilmViewModel (filmDataManager: FilmDataManager): FilmViewModel =
        FilmViewModel(filmDataManager)

}