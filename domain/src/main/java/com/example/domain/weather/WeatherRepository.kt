package com.example.domain.weather

import com.example.domain.weather.model.CurrentWeather
import com.example.domain.weather.model.FutureWeather

interface WeatherRepository {

    suspend fun getCurrentWeather(city: String): Result<CurrentWeather>

    suspend fun getFutureWeather(city: String): Result<FutureWeather>
}