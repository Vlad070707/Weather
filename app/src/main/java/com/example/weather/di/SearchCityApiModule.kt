package com.example.weather.di

import com.example.weather.data.api.ApiConstants
import com.example.weather.data.api.SearchCityApi
import com.example.weather.data.repository.SearchCityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchCityApiModule {

  @Provides
  @Singleton
  fun provideApi(): SearchCityApi {
    return Retrofit.Builder()
      .baseUrl(ApiConstants.CITY_BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(SearchCityApi::class.java)
  }

  @Provides
  @Singleton
  fun provideSearchCityRepository(
    api: SearchCityApi
  ) = SearchCityRepository(api)
}