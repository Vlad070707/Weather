package com.example.weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
  private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

  private val _currentCityState = MutableStateFlow("")
  val currentCityState: StateFlow<String> = _currentCityState

  init {
    viewModelScope.launch {
      userPreferencesRepository.getCity.collect { city ->
        _currentCityState.value = city
      }
    }
  }
}