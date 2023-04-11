package com.example.domain.weather.usecase

import com.example.domain.weather.model.CurrentWeatherDto
import com.example.domain.util.RequestState
import com.example.domain.weather.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadCurrentWeatherUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(
        city: String
    ): RequestState<CurrentWeatherDto> {
        return withContext(Dispatchers.IO) {
            repository.getCurrentWeather(city = city)
        }
    }
}