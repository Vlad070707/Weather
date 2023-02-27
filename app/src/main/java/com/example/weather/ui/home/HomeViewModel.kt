package com.example.weather.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.api.weather_models.CurrentWeatherDto
import com.example.weather.data.api.weather_models.FutureWeatherDto
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.data_store.UserPreferencesRepository
import com.example.weather.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val weatherRepository: WeatherRepository,
  private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

  private val _currentWeatherState = MutableStateFlow<Resource<CurrentWeatherDto>>(Resource.Loading())
  val currentWeatherState: StateFlow<Resource<CurrentWeatherDto>> = _currentWeatherState

  private val _futureWeatherState = MutableStateFlow<Resource<FutureWeatherDto>>(Resource.Loading())
  val futureWeatherState: StateFlow<Resource<FutureWeatherDto>> = _futureWeatherState

  private val _currentCityState = MutableStateFlow("")

  init {
    initCity()
    updateWeatherData()
  }

  fun initCity() {
    viewModelScope.launch {
      userPreferencesRepository.getCity.collect { city ->
        city.let {
          _currentCityState.value = it
        }
      }
    }
  }

  fun updateWeatherData() {
    viewModelScope.launch {
      launch(Dispatchers.IO) {
        _currentWeatherState.value = weatherRepository.getCurrentWeather(_currentCityState.value)
      }
      launch(Dispatchers.IO) {
        _futureWeatherState.value = weatherRepository.getFutureWeather(_currentCityState.value)
      }
    }
  }
}