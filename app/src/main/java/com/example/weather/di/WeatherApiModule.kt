package com.example.weather.di

import com.example.weather.data.api.ApiConstants.WEATHER_BASE_URL
import com.example.weather.data.api.WeatherApi
import com.example.weather.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherApiModule {

  @Provides
  @Singleton
  fun provideApi(): WeatherApi {
    return Retrofit.Builder()
      .baseUrl(WEATHER_BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(WeatherApi::class.java)
  }

  @Provides
  @Singleton
  fun provideWeatherRepo(
    api: WeatherApi
  ) = WeatherRepository(api)
}