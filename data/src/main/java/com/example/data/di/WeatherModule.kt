package com.example.data.di

import com.example.data.repository.SearchRepositoryImpl
import com.example.data.repository.WeatherRepositoryImpl
import com.example.data.service.SearchCityService
import com.example.data.service.WeatherService
import com.example.data.usecase.SearchCityUseCaseImpl
import com.example.domain.search.SearchRepository
import com.example.domain.search.usecase.SearchCityUseCase
import com.example.domain.weather.WeatherRepository
import com.example.domain.weather.usecase.LoadCurrentWeatherUseCase
import com.example.domain.weather.usecase.LoadFutureWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object WeatherModule {

    @Provides
    fun provideWeatherRepository(weatherService: WeatherService): WeatherRepository =
        WeatherRepositoryImpl(weatherService)

    @Provides
    fun provideSearchRepository(searchCityService: SearchCityService): SearchRepository =
        SearchRepositoryImpl(searchCityService)

    @Provides
    fun provideLoadCurrentWeatherUseCase(
        repository: WeatherRepository
    ) = LoadCurrentWeatherUseCase(repository)

    @Provides
    fun provideLoadFutureWeatherUseCase(
        repository: WeatherRepository
    ) = LoadFutureWeatherUseCase(repository)

    @Provides
    fun provideSearchCityUseCase(
        repository: SearchRepository
    ): SearchCityUseCase = SearchCityUseCaseImpl(repository)

}