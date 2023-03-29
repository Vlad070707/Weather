package com.example.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.data.weather.model.CurrentWeatherDto
import com.example.data.weather.model.FutureWeatherDto
import com.example.domain.location.usecase.GetSavedLocationUseCase
import com.example.domain.weather.usecase.LoadCurrentWeatherUseCase
import com.example.domain.weather.usecase.LoadFutureWeatherUseCase
import com.example.presentation.base.BaseViewModel
import com.example.domain.util.Resource
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
            _currentWeatherState.value = loadCurrentWeatherUseCase(city = currentCityState.value)
            _futureWeatherState.value = loadFutureWeatherUseCase(city = currentCityState.value)
        }
    }

    private fun setLoadingStatusForWeatherStates() {
        _currentWeatherState.value = Resource.Loading()
        _futureWeatherState.value = Resource.Loading()
    }
}