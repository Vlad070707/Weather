package com.example.domain.weather.usecase

import com.example.domain.weather.model.FutureWeather

interface LoadFutureWeatherUseCase {

    suspend operator fun invoke(city: String): Result<FutureWeather>
}