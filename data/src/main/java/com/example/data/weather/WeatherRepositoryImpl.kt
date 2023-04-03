package com.example.data.weather

import com.example.domain.weather.model.CurrentWeatherDto
import com.example.domain.weather.model.FutureWeatherDto
import com.example.domain.util.Resource
import com.example.domain.weather.WeatherRepository

class WeatherRepositoryImpl(private val service: WeatherService) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): Resource<CurrentWeatherDto> {
        val response = try {
            service.getCurrentWeather(city)
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage)
        }
        return Resource.Success(response)
    }

    override suspend fun getFutureWeather(city: String): Resource<FutureWeatherDto> {
        val response = try {
            service.getFutureWeather(city)
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage)
        }
        return Resource.Success(response)
    }
}