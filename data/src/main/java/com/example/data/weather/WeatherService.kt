package com.example.data.weather

import com.example.data.weather.WeatherConstants.API_KEY
import com.example.data.weather.WeatherConstants.END_POINTS_CURRENT_WEATHER
import com.example.data.weather.WeatherConstants.END_POINTS_FUTURE_WEATHER
import com.example.data.weather.WeatherConstants.UNITS
import com.example.data.weather.model.CurrentWeatherDto
import com.example.data.weather.model.FutureWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(END_POINTS_CURRENT_WEATHER)
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String = UNITS,
        @Query("appid") appid: String = API_KEY
    ): CurrentWeatherDto

    @GET(END_POINTS_FUTURE_WEATHER)
    suspend fun getFutureWeather(
        @Query("q") city: String,
        @Query("units") units: String = UNITS,
        @Query("appid") appid: String = API_KEY
    ): FutureWeatherDto
}