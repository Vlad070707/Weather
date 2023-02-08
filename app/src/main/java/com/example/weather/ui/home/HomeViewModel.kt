package com.example.weather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.api.models.CurrentWeatherDto
import com.example.weather.data.api.models.FutureWeatherDto
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val weatherRepository: WeatherRepository
) : ViewModel() {

  private val _currentWeatherState = MutableStateFlow<Resource<CurrentWeatherDto>>(Resource.Error(""))
  val currentWeatherState: StateFlow<Resource<CurrentWeatherDto>> = _currentWeatherState

  private val _futureWeatherState = MutableStateFlow<Resource<FutureWeatherDto>>(Resource.Loading())
  val futureWeatherState: StateFlow<Resource<FutureWeatherDto>> = _futureWeatherState

  init {
    getWeather()
  }

  fun getWeather() {
    viewModelScope.launch {
      launch(Dispatchers.IO) {
        _currentWeatherState.value = weatherRepository.getCurrentWeather()
      }
      launch(Dispatchers.IO) {
        _futureWeatherState.value = weatherRepository.getFutureWeather()
      }
    }
  }
}