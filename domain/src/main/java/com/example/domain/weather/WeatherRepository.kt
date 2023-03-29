package com.example.domain.weather

import com.example.data.weather.model.CurrentWeatherDto
import com.example.data.weather.model.FutureWeatherDto
import com.example.domain.util.Resource

interface WeatherRepository {

    suspend fun getCurrentWeather(city: String): Resource<CurrentWeatherDto>

    suspend fun getFutureWeather(city: String): Resource<FutureWeatherDto>
}