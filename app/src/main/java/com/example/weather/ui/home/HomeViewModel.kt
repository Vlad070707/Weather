package com.example.weather.ui.home

import androidx.lifecycle.viewModelScope
import com.example.weather.data.api.weather_models.CurrentWeatherDto
import com.example.weather.data.api.weather_models.FutureWeatherDto
import com.example.weather.data.repository.UserPreferencesRepository
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.ui.BaseViewModel
import com.example.weather.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val weatherRepository: WeatherRepository,
  userPreferencesRepository: UserPreferencesRepository
) : BaseViewModel(userPreferencesRepository) {

  private val _currentWeatherState = MutableStateFlow<Resource<CurrentWeatherDto>>(Resource.Loading())
  val currentWeatherState: StateFlow<Resource<CurrentWeatherDto>> = _currentWeatherState

  private val _futureWeatherState = MutableStateFlow<Resource<FutureWeatherDto>>(Resource.Loading())
  val futureWeatherState: StateFlow<Resource<FutureWeatherDto>> = _futureWeatherState

  init {
    updateWeatherData()
  }

  fun updateWeatherData() {
    setLoadingStatusForWeatherStates()
    viewModelScope.launch {
      _currentWeatherState.value = withContext(Dispatchers.IO) {
        weatherRepository.getCurrentWeather(currentCityState.value)
      }
      _futureWeatherState.value = withContext(Dispatchers.IO) {
        weatherRepository.getFutureWeather(currentCityState.value)
      }
    }
  }

  private fun setLoadingStatusForWeatherStates(){
    _currentWeatherState.value = Resource.Loading()
    _futureWeatherState.value = Resource.Loading()
  }
}