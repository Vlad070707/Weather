package com.example.domain.weather.usecase

import com.example.domain.weather.model.FutureWeatherDto
import com.example.domain.util.RequestState
import com.example.domain.weather.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadFutureWeatherUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(
        city: String
    ): RequestState<FutureWeatherDto> {
        return withContext(Dispatchers.IO) {
            repository.getFutureWeather(city = city)
        }
    }
}