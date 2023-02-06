package com.example.weather.data.api

import com.example.weather.data.api.ApiConstants.END_POINTS_CURRENT_WEATHER
import com.example.weather.data.api.model.CurrentWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

  @GET(END_POINTS_CURRENT_WEATHER)
  suspend fun getCurrentWeather(
    @Query("q") city: String,
    @Query("units") units: String
  ): CurrentWeatherDto
}