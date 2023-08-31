package com.example.data.repository

import com.example.data.service.WeatherService
import com.example.domain.weather.model.CurrentWeatherDto
import com.example.domain.weather.model.FutureWeatherDto
import com.example.domain.util.RequestState
import com.example.domain.weather.WeatherRepository

class WeatherRepositoryImpl(private val service: WeatherService) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): RequestState<CurrentWeatherDto> {
        val response = try {
            service.getCurrentWeather(city)
        } catch (e: Exception) {
            return RequestState.Error(e.localizedMessage)
        }
        return RequestState.Success(response)
    }

    override suspend fun getFutureWeather(city: String): RequestState<FutureWeatherDto> {
        val response = try {
            service.getFutureWeather(city)
        } catch (e: Exception) {
            return RequestState.Error(e.localizedMessage)
        }
        return RequestState.Success(response)
    }
}