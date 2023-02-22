package com.example.weather.data.api

import com.example.weather.data.api.ApiConstants.API_KEY
import com.example.weather.data.api.ApiConstants.END_POINTS_CURRENT_WEATHER
import com.example.weather.data.api.ApiConstants.END_POINTS_FUTURE_WEATHER
import com.example.weather.data.api.city_models.SearchCityDto
import com.example.weather.data.api.weather_models.CurrentWeatherDto
import com.example.weather.data.api.weather_models.FutureWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

  @GET(END_POINTS_CURRENT_WEATHER)
  suspend fun getCurrentWeather(
    @Query("q") city: String,
    @Query("units") units: String,
    @Query("appid") appid: String = API_KEY
  ): CurrentWeatherDto

  @GET(END_POINTS_FUTURE_WEATHER)
  suspend fun getFutureWeather(
    @Query("q") city: String,
    @Query("units") units: String,
    @Query("appid") appid: String = API_KEY
  ): FutureWeatherDto
}