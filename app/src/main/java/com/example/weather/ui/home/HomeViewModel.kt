package com.example.weather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.api.model.CurrentWeatherDto
import com.example.weather.data.repository.WeatherRepo
import com.example.weather.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val weatherRepo: WeatherRepo
) : ViewModel() {

  private val _state = MutableStateFlow<Resource<CurrentWeatherDto>>(Resource.Loading())
  val state: StateFlow<Resource<CurrentWeatherDto>>
    get() = _state

  init {
    viewModelScope.launch {
      val currentWeather = weatherRepo.getCurrentWeather()
      _state.value = currentWeather
    }
  }
}