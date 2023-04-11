package com.example.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.domain.weather.model.CurrentWeatherDto
import com.example.domain.weather.model.FutureWeatherDto
import com.example.domain.location.usecase.GetSavedLocationUseCase
import com.example.domain.weather.usecase.LoadCurrentWeatherUseCase
import com.example.domain.weather.usecase.LoadFutureWeatherUseCase
import com.example.presentation.base.BaseViewModel
import com.example.domain.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadCurrentWeatherUseCase: LoadCurrentWeatherUseCase,
    private val loadFutureWeatherUseCase: LoadFutureWeatherUseCase,
    getSavedLocationUseCase: GetSavedLocationUseCase
) : BaseViewModel(getSavedLocationUseCase) {

    private val _currentWeatherState = MutableStateFlow<RequestState<CurrentWeatherDto>>(RequestState.Loading())
    val currentWeatherState: StateFlow<RequestState<CurrentWeatherDto>> = _currentWeatherState

    private val _futureWeatherState = MutableStateFlow<RequestState<FutureWeatherDto>>(RequestState.Loading())
    val futureWeatherState: StateFlow<RequestState<FutureWeatherDto>> = _futureWeatherState

    init {
        updateWeatherData()
    }

    fun updateWeatherData() {
        setLoadingStatusForWeatherStates()
        viewModelScope.launch {
            _currentWeatherState.value = loadCurrentWeatherUseCase(city = currentCityState.value.data ?: "")
            _futureWeatherState.value = loadFutureWeatherUseCase(city = currentCityState.value.data ?: "")
        }
    }

    private fun setLoadingStatusForWeatherStates() {
        _currentWeatherState.value = RequestState.Loading()
        _futureWeatherState.value = RequestState.Loading()
    }
}