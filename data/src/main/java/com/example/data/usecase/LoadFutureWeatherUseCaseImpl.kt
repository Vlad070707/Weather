package com.example.data.usecase

import com.example.domain.weather.WeatherRepository
import com.example.domain.weather.model.FutureWeather
import com.example.domain.weather.usecase.LoadFutureWeatherUseCase

class LoadFutureWeatherUseCaseImpl(private val repository: WeatherRepository) : LoadFutureWeatherUseCase {

    override suspend fun invoke(city: String): Result<FutureWeather> = runCatching {
        repository.getFutureWeather(city = city).getOrThrow()
    }
}