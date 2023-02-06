package com.example.weather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.api.model.CurrentWeatherDto
import com.example.weather.data.api.model.FutureWeatherDto
import com.example.weather.data.repository.WeatherRepo
import com.example.weather.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val weatherRepo: WeatherRepo
) : ViewModel() {

  private val _currentWeatherState = MutableStateFlow<Resource<CurrentWeatherDto>>(Resource.Error(""))
  val currentWeatherState: StateFlow<Resource<CurrentWeatherDto>>
    get() = _currentWeatherState

  private val _futureWeatherState = MutableStateFlow<Resource<FutureWeatherDto>>(Resource.Loading())
  val futureWeatherState: StateFlow<Resource<FutureWeatherDto>>
    get() = _futureWeatherState

  init {
    viewModelScope.launch {
      launch(Dispatchers.IO) {
        _currentWeatherState.value = weatherRepo.getCurrentWeather()
      }
      launch(Dispatchers.IO) {
        _futureWeatherState.value = weatherRepo.getFutureWeather()
      }
    }
  }
}