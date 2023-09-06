package com.example.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.weather.model.CurrentWeather
import com.example.domain.weather.model.FutureWeather
import com.example.domain.location.usecase.GetSavedLocationUseCase
import com.example.domain.weather.usecase.LoadCurrentWeatherUseCase
import com.example.domain.weather.usecase.LoadFutureWeatherUseCase
import com.example.presentation.home.view.CurrentWeatherViewState
import com.example.presentation.home.view.WeatherForNextDaysViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadCurrentWeatherUseCase: LoadCurrentWeatherUseCase,
    private val loadFutureWeatherUseCase: LoadFutureWeatherUseCase,
    private val getSavedLocationUseCase: GetSavedLocationUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getSavedLocationUseCase().collect { city ->
                _uiState.update {
                    it.copy(
                        currentCity = city
                    )
                }
            }
        }
        updateWeatherData()
    }

    fun updateWeatherData() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            var isError = false
            val currentWeather: CurrentWeather = loadCurrentWeatherUseCase(city = _uiState.value.currentCity)
                .fold(
                    onSuccess = {
                        it
                    },
                    onFailure = {
                        Timber.e(it)
                        isError = true
                        CurrentWeather()
                    }
                )
            val futureWeather: FutureWeather = loadFutureWeatherUseCase(city = _uiState.value.currentCity)
                .fold(
                    onSuccess = {
                        it
                    },
                    onFailure = {
                        Timber.e(it)
                        isError = true
                        FutureWeather()
                    }
                )
            _uiState.update {
                it.copy(
                    currentWeather = it.currentWeather.copy(currentWeather = currentWeather),
                    futureWeather = it.futureWeather.copy(futureWeather = futureWeather),
                    isLoading = false,
                    isError = isError
                )
            }
        }
    }

    fun onShowMoreDetailsClicked(showMoreDetails: Boolean) {
        _uiState.update {
            it.copy(
                currentWeather = it.currentWeather.copy(showMoreDetails = showMoreDetails)
            )
        }
    }

    fun onDayOptionClicked(
        isTodayChecked: Boolean = false,
        isTomorrowChecked: Boolean = false,
        isNextFiveDaysChecked: Boolean = false
    ) {
        _uiState.update {
            it.copy(
                futureWeather = it.futureWeather.copy(
                    daysOfWeatherViewState = it.futureWeather.daysOfWeatherViewState.copy(
                        isTodayChecked = isTodayChecked,
                        isTomorrowChecked = isTomorrowChecked,
                        isNextFiveDaysChecked = isNextFiveDaysChecked
                    )
                )
            )
        }
    }
}

data class HomeUiState(
    val currentCity: String = "",
    val currentWeather: CurrentWeatherViewState = CurrentWeatherViewState(),
    val futureWeather: WeatherForNextDaysViewState = WeatherForNextDaysViewState(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)