package com.example.weather.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.api.city_models.ListOfHintsDto
import com.example.weather.data.repository.SearchCityRepository
import com.example.weather.data_store.UserPreferencesRepository
import com.example.weather.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
  private val searchCityRepository: SearchCityRepository,
  private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

  private val _listOfHintsDtoState = MutableStateFlow<Resource<ListOfHintsDto>>(Resource.Error())
  val listOfHintsDtoState: StateFlow<Resource<ListOfHintsDto>> = _listOfHintsDtoState

  private val _currentCityState = MutableStateFlow("")
  val currentCityState: StateFlow<String> = _currentCityState

  init {
    viewModelScope.launch {
      userPreferencesRepository.getCity.collect { city ->
        city.let {
          _currentCityState.value = it
        }
      }
    }
  }

  fun searchCity(query: String) {
    viewModelScope.launch {
      _listOfHintsDtoState.value = Resource.Loading()
      _listOfHintsDtoState.value = searchCityRepository.searchCity(query)
    }
  }

  fun saveCurrentCity(city: String) {
    viewModelScope.launch {
      userPreferencesRepository.saveCity(city)
    }
  }
}