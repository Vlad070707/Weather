package com.example.weather.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.api.city_models.SearchCityDto
import com.example.weather.data.repository.SearchCityRepository
import com.example.weather.data.repository.WeatherRepository
import com.example.weather.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
  private val searchCityRepository: SearchCityRepository
) : ViewModel() {

  private val _searchCityState = MutableStateFlow<Resource<SearchCityDto>>(Resource.Loading())
  val searchCityState: StateFlow<Resource<SearchCityDto>> = _searchCityState

  fun searchCity(query: String) {
    viewModelScope.launch {
      val test = searchCityRepository.searchCity(query)
      _searchCityState.value = test
    }
  }
}