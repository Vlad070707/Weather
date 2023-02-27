package com.example.weather.data.repository

import com.example.weather.data.api.WeatherApi
import com.example.weather.data.api.weather_models.CurrentWeatherDto
import com.example.weather.data.api.weather_models.FutureWeatherDto
import com.example.weather.util.Resource
import javax.inject.Inject

class WeatherRepository @Inject constructor(
  private val weatherApi: WeatherApi
) {

  suspend fun getCurrentWeather(city: String): Resource<CurrentWeatherDto> {
    val response = try {
      weatherApi.getCurrentWeather(city)
    } catch (e: Exception) {
      return Resource.Error(e.localizedMessage)
    }
    return Resource.Success(response)
  }

  suspend fun getFutureWeather(city: String): Resource<FutureWeatherDto> {
    val response = try {
      weatherApi.getFutureWeather(city)
    } catch (e: Exception) {
      return Resource.Error(e.localizedMessage)
    }
    return Resource.Success(response)
  }
}