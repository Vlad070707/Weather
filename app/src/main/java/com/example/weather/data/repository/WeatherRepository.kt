package com.example.weather.data.repository

import com.example.weather.data.api.WeatherApi
import com.example.weather.data.api.models.CurrentWeatherDto
import com.example.weather.data.api.models.FutureWeatherDto
import com.example.weather.util.Resource
import javax.inject.Inject

class WeatherRepository @Inject constructor(
  private val weatherApi: WeatherApi
) {

  suspend fun getCurrentWeather(): Resource<CurrentWeatherDto> {
    val response = try {
      weatherApi.getCurrentWeather("Derazhnya", "metric")
    } catch (e: Exception) {
      return Resource.Error(e.localizedMessage)
    }
    return Resource.Success(response)
  }

  suspend fun getFutureWeather(): Resource<FutureWeatherDto> {
    val response = try {
      weatherApi.getFutureWeather("Derazhnya", "metric")
    } catch (e: Exception) {
      return Resource.Error(e.localizedMessage)
    }
    return Resource.Success(response)
  }
}