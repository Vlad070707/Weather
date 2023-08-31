package com.example.weather.di

import com.example.data.service.SearchCityService
import com.example.data.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    private const val BASE_URL_WEATHER = "https://api.openweathermap.org/data/2.5/"

    private const val BASE_URL_SEARCH = "http://api.geonames.org/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()


    @Singleton
    @Provides
    fun provideWeatherService(okHttpClient: OkHttpClient): WeatherService =
        Retrofit.Builder()
            .baseUrl(BASE_URL_WEATHER)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WeatherService::class.java)

    @Singleton
    @Provides
    fun provideSearchCityService(okHttpClient: OkHttpClient): SearchCityService =
        Retrofit.Builder()
            .baseUrl(BASE_URL_SEARCH)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SearchCityService::class.java)
}