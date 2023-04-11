package com.example.domain.weather

import com.example.domain.weather.model.CurrentWeatherDto
import com.example.domain.weather.model.FutureWeatherDto
import com.example.domain.util.RequestState

interface WeatherRepository {

    suspend fun getCurrentWeather(city: String): RequestState<CurrentWeatherDto>

    suspend fun getFutureWeather(city: String): RequestState<FutureWeatherDto>
}