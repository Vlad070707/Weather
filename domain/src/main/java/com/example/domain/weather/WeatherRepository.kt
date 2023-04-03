package com.example.domain.weather

import com.example.domain.weather.model.CurrentWeatherDto
import com.example.domain.weather.model.FutureWeatherDto
import com.example.domain.util.Resource

interface WeatherRepository {

    suspend fun getCurrentWeather(city: String): Resource<CurrentWeatherDto>

    suspend fun getFutureWeather(city: String): Resource<FutureWeatherDto>
}