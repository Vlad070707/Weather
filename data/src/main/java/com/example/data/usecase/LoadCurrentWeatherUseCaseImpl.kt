package com.example.data.usecase

import com.example.domain.weather.WeatherRepository
import com.example.domain.weather.model.CurrentWeather
import com.example.domain.weather.usecase.LoadCurrentWeatherUseCase

class LoadCurrentWeatherUseCaseImpl(private val repository: WeatherRepository) : LoadCurrentWeatherUseCase {

    override suspend fun invoke(city: String): Result<CurrentWeather> = runCatching {
        repository.getCurrentWeather(city = city).getOrThrow()
    }
}