package com.example.starwarsdk.network.di

import com.example.starwarsdk.BuildConfig
import com.example.starwarsdk.network.api.FilmApiService
import com.example.starwarsdk.network.api.PeopleApiService
import com.example.starwarsdk.network.api.PlanetApiService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val STARWARS_BASE_URL = "https://swapi.dev/"

    @Provides
    @Singleton
    fun provideNetworkURL(): String = STARWARS_BASE_URL

    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    private fun okHttpClient(logger: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor((logger))
        .readTimeout(3, TimeUnit.MINUTES)
        .connectTimeout(3, TimeUnit.MINUTES)
        .writeTimeout(3, TimeUnit.MINUTES)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(logger: HttpLoggingInterceptor): Retrofit =
        Retrofit.Builder().baseUrl(provideNetworkURL()).client(okHttpClient(logger))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()

    @Provides
    @Singleton
    fun providePeopleAPIService(retrofit: Retrofit): PeopleApiService =
        retrofit.create(PeopleApiService::class.java)

    @Provides
    @Singleton
    fun providePlanetAPIService(retrofit: Retrofit): PlanetApiService =
        retrofit.create(PlanetApiService::class.java)

    @Provides
    @Singleton
    fun provideFilmAPIService(retrofit: Retrofit): FilmApiService =
        retrofit.create(FilmApiService::class.java)

}