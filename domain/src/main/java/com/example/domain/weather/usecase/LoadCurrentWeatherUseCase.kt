package com.example.domain.weather.usecase

import com.example.domain.weather.model.CurrentWeather

interface LoadCurrentWeatherUseCase {

    suspend operator fun invoke(city: String): Result<CurrentWeather>
}