package com.example.data.repository

import com.example.data.service.WeatherService
import com.example.domain.weather.model.CurrentWeather
import com.example.domain.weather.model.FutureWeather
import com.example.domain.weather.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(private val service: WeatherService) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): Result<CurrentWeather> = withContext(Dispatchers.IO) {
        try {
            val response = service.getCurrentWeather(city)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFutureWeather(city: String): Result<FutureWeather> = withContext(Dispatchers.IO) {
        try {
            val response = service.getFutureWeather(city)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}