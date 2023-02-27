package com.example.weather.ui.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data_store.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
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