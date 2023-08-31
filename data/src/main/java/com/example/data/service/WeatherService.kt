package com.example.data.service

import com.example.domain.weather.model.CurrentWeatherDto
import com.example.domain.weather.model.FutureWeatherDto
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

    companion object {
        private const val END_POINTS_CURRENT_WEATHER = "weather?"
        private const val END_POINTS_FUTURE_WEATHER = "forecast?"
        private const val API_KEY = "61f71b16627b1aacb1e4b316c0557b79"
        private const val UNITS = "metric"
    }
}