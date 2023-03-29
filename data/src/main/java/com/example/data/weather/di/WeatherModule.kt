package com.example.data.weather.di

import com.example.data.weather.WeatherRepositoryImpl
import com.example.data.weather.WeatherService
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
    fun provideLoadCurrentWeatherUseCase(
        repository: WeatherRepository
    ) = LoadCurrentWeatherUseCase(repository)

    @Provides
    fun provideLoadFutureWeatherUseCase(
        repository: WeatherRepository
    ) = LoadFutureWeatherUseCase(repository)
}